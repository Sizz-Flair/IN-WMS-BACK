package com.wms.inwms.controller;

import com.wms.inwms.domain.receive.Receive;
import com.wms.inwms.domain.receive.ReceiveService;
import com.wms.inwms.domain.receive.receivelist.ReceiveList;
import com.wms.inwms.domain.receive.receivelist.ReceiveListService;
import com.wms.inwms.security.TokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReceiveController {
    private final ReceiveService receiveService;
    private final ReceiveListService receiveListService;
    private final TokenProperties tokenProperties;

    @PostMapping("/receiving")
    private Receive receiving(@RequestHeader("Authorization") String token, @RequestBody List<String> hwbNoList) {
        try {
            return saveReceive(hwbNoList, token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/receiving/initdata")
    private ResponseEntity<List<Receive>> findReceivingList() {
        try{
            return ResponseEntity.ok(receiveService.findByCreatedBetween());
        } catch(NullPointerException e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @Transactional
    protected Receive saveReceive(List<String> hwbNoList, String token) throws Exception {
        Receive receive = Receive.builder()
                .receiveNumber(createReceiveNumber(tokenProperties.getTokenInfo(token)))
                .amount(new BigDecimal(hwbNoList.size())).locationId(1L).build();
        Receive resultReceiveInfo = receiveService.save(receive);
        receiveListService.saveAll(saveReceiveList(hwbNoList));

        return resultReceiveInfo;
    }

    private String createReceiveNumber(Claims tokenInfo) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return "RE-"+tokenInfo.get("agent")+"-"+localDateTime.format(formatter);
    }

    private List<ReceiveList> saveReceiveList(List<String> hwbNoList) {
        List<ReceiveList> result = new ArrayList<>();
        for (String hwbNo : hwbNoList) {
            ReceiveList receiveList = ReceiveList.builder().hwbNo(hwbNo).receiveNumber("RE000").build();
            result.add(receiveList);
        }
        return result;
    }
}
