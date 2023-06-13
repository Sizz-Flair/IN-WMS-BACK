package com.wms.inwms.controller;

import com.wms.inwms.domain.inbound.InboundService;
import com.wms.inwms.domain.inbound.dto.InboundResultDto;
import com.wms.inwms.domain.inbound.dto.InboundSaveDto;
import com.wms.inwms.domain.response.ResponseData;
import com.wms.inwms.domain.response.ResponseMessage;
import com.wms.inwms.domain.response.ResultDataList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(path="/mapping/inbound")
    private ResponseEntity<ResultDataList> inboundMapping(@RequestBody List<@Valid InboundSaveDto> saveDto) {
        List<InboundResultDto.InboundSaveResultDto> resultData = inboundService.saveInboundMapping(saveDto);
        return ResponseEntity.ok().body(responseData.ResultListData(resultData, ResponseMessage.SUCCESS.name()));
    }
}
