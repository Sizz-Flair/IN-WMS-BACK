package com.wms.inwms.controller;

import com.wms.inwms.domain.receive.Receive;
import com.wms.inwms.domain.receive.ReceiveService;
import com.wms.inwms.domain.receive.receivelist.ReceiveList;
import com.wms.inwms.domain.receive.receivelist.ReceiveListService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReceiveController {
    private final ReceiveService receiveService;
    private final ReceiveListService receiveListService;

    @PostMapping("/receiving")
    private Receive receiving(@RequestBody List<String> hwbNoList) {
        try {
            return saveReceive(hwbNoList);
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

    @GetMapping("/find/receiving")
    private List<Receive> findReceiving() {
        try {
            return receiveService.findAll();
        } catch (Exception e) {

        }

        return null;
    }

    @Transactional
    protected Receive saveReceive(List<String> hwbNoList) throws Exception {
        Receive receive = Receive.builder()
                .receiveNumber("RE000")
                .amount(new BigDecimal(hwbNoList.size())).locationId(1L).build();
        Receive resultReceiveInfo = receiveService.save(receive);
        receiveListService.saveAll(saveReceiveList(hwbNoList));

        return resultReceiveInfo;
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
