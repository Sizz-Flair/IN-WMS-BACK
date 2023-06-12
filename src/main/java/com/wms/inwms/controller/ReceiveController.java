package com.wms.inwms.controller;

import com.wms.inwms.domain.agent.AgentService;
import com.wms.inwms.domain.mapper.cj.CJDeliveryDto;
import com.wms.inwms.domain.mapper.cj.CjMapper;
import com.wms.inwms.domain.receive.Receive;
import com.wms.inwms.domain.receive.ReceiveDto;
import com.wms.inwms.domain.receive.ReceiveService;
import com.wms.inwms.domain.receive.receivelist.ReceiveList;
import com.wms.inwms.domain.receive.receivelist.ReceiveListService;
import com.wms.inwms.domain.response.ResponseData;
import com.wms.inwms.domain.response.ResultData;
import com.wms.inwms.domain.response.ResultDataList;
import com.wms.inwms.security.TokenProperties;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class ReceiveController {

    private final ReceiveService receiveService;
    private final ReceiveListService receiveListService;
    private final TokenProperties tokenProperties;
    private final ResponseData responseData;
    private final AgentService agentService;
    private final CjMapper cjMapper;

    @PostMapping("/ttt")
    private void ttt() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYYMMdd");

        //DateTimeFormatter.ofPattern("YYYYMMdd").format(Instant.now(Clock.systemUTC()));

        LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"));
        String mpckey = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd")) + "_30244464" + "567213753691";
        String rcvrNm = "김현진";
        String rcvrTelNo1 = "010";
        String rcvrTelNo2 = "4024";
        String rcvrTelNo3 = "3740";
        String rcvrZipNo = "21400";
        String rcvrAddr = "인천광역시 부평구 안남로 15번길 24";
        String rcvrdetailAddr = "중앙하이츠 102동 1601호";
        String ORI_INVC_NO = "567213753691";
        String gdsNm = "슈퍼컴퓨터";
        Long gdsQty = 1L;

        CJDeliveryDto cjDto2 = CJDeliveryDto.builder()
                .custUseNo("TESTORDER-4") // 송장과 1:1 매칭
                .oriOrdNo("TESTORDER-ORI-4")
                .mpckKey(mpckey + ORI_INVC_NO)
                .sendrNm(rcvrNm)
                .sendrTelNo1(rcvrTelNo1)
                .sendrTelNo2(rcvrTelNo2)
                .sendrTelNo3(rcvrTelNo3)
                .sendrZipNo(rcvrZipNo)
                .sendrAddr(rcvrAddr)
                .sendrDetailAddr(rcvrdetailAddr)
                .gdsNm(gdsNm)
                .gdsQty(gdsQty)
                .remark1("remark").prtSt("02")
                .build();

//        CJDto2 cjDto2 = CJDto2.builder()
//                .custUseNo("3870307236412")
//                .mpckKey(mpckey)
//                .rcvrNm(rcvrNm)
//                .rcvrTelNo1(rcvrTelNo1)
//                .rcvrTelNo2(rcvrTelNo2)
//                .rcvrTelNo3(rcvrTelNo3)
//                .rcvrZipNo(rcvrZipNo)
//                .rcvrAddr(rcvrAddr)
//                .rcvrDetailAddr(rcvrdetailAddr)
//                .oriInvcNo(ORI_INVC_NO)
//                .gdsNm(gdsNm)
//                .gdsQty(gdsQty).build();

        //cjMapper.send(cjDto2);
        System.out.println("test");


    }
    @PostMapping("/receiving")
    private ResponseEntity<ResultData> receiving(@RequestHeader("Authorization") String token, @RequestBody List<String> hwbNoList) {
        try {
            Receive resultReceiveData = saveReceive(hwbNoList, token);
            ResultData resultData = responseData.ResultData(resultReceiveData, "SUCCESS");
            return ResponseEntity.ok(resultData);
        } catch (Exception e) {
            String message = "data";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData.ResultDataMessage(message));
        }
    }

    @PostMapping("/receiving/initdata")
    private ResponseEntity<ResultDataList> findReceivingList() {
        try{
            List<Receive> receiveListData = receiveService.findByCreatedBetween();
            ResultDataList resulDataList = responseData.ResultListData(receiveListData, "SUCCESS");
            return ResponseEntity.ok(resulDataList);
        } catch(NullPointerException e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/receiving/search")
    private ResponseEntity<ResultDataList> findSearchReceiveData(@RequestBody ReceiveDto receiveDto) {

        String receiveNumber = receiveDto.getReceiveNumber().orElse("");
        Instant startDate = receiveDto.getStartDate().orElse(Instant.EPOCH);
        Instant endDate = receiveDto.getEndDate().orElse(Instant.now());

        List<Receive> receiveListData = receiveService.searchReceivingData(startDate, endDate, receiveNumber);

        return ResponseEntity.ok(responseData.ResultListData(receiveListData, "SUCCESS"));
    }

    @Transactional
    protected Receive saveReceive(List<String> hwbNoList, String token) throws Exception {
        try{
            Receive receive = Receive.builder()
                    .receiveNumber(createReceiveNumber(tokenProperties.getTokenInfo(token)))
                    .amount(new BigDecimal(hwbNoList.size())).locationId(1L).build();
            Receive resultReceiveInfo = receiveService.save(receive);
            receiveListService.saveAll(saveReceiveList(hwbNoList));

            return resultReceiveInfo;
        } catch (Exception e) {
            throw new Exception();
        }
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