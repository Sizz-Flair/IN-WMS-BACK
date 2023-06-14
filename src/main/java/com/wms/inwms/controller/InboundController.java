package com.wms.inwms.controller;

import com.wms.inwms.domain.inbound.InboundService;
import com.wms.inwms.domain.inbound.dto.InboundResultDto;
import com.wms.inwms.domain.inbound.dto.InboundSaveDto;
import com.wms.inwms.domain.response.ResponseData;
import com.wms.inwms.domain.response.ResponseMessage;
import com.wms.inwms.domain.response.ResultDataList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * packageName    : com.wms.inwms.controller
 * fileName       : InboundController
 * author         : akfur
 * date           : 2023-06-12
 */
@RestController
@RequiredArgsConstructor
public class InboundController {

    private final InboundService inboundService;
    private final ResponseData responseData;

    @PostMapping(path = "/mapping/inbound/{up}/{low}")
    private ResponseEntity<ResultDataList> inboundMapping(
            @RequestBody List<@Valid InboundSaveDto> saveDto,
            @PathVariable String up,
            @PathVariable String low
    ) {
        List<InboundResultDto.InboundSaveResultDto> resultData =
                inboundService.saveInboundMapping(saveDto, up, low);
        return ResponseEntity.ok().body(responseData.ResultListData(resultData, ResponseMessage.SUCCESS.name()));
    }

    @GetMapping(path="/search/mapping")


    @PostMapping(path = "/test22")
    private void test() {
        inboundService.test();
    }
}
