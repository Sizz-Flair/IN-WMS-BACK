package com.wms.inwms.controller;

import com.wms.inwms.domain.inbound.InboundService;
import com.wms.inwms.domain.inbound.dto.InboundResultDto;
import com.wms.inwms.domain.location.upperlocation.UpperLocationService;
import com.wms.inwms.domain.location.upperlocation.dto.UpperLocationDto;
import com.wms.inwms.domain.response.ResponseData;
import com.wms.inwms.domain.response.ResponseMessage;
import com.wms.inwms.domain.response.ResultDataList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.wms.inwms.controller
 * fileName       : StorageController
 * author         : akfur
 * date           : 2023-07-06
 */
@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {

    private final UpperLocationService upperLocationService;
    private final InboundService inboundService;
    private final ResponseData responseData;

    /**
     * ==============================================
     * <p> 재고내역 모두 검색
     * ==============================================
     * user : akfur
     * date : 2023-07-06
     *
     * @return ResponseEntity<ResultDataList>
     */
    @GetMapping(path = "/serach")
    private ResponseEntity<ResultDataList> searchStorage() {
        List<UpperLocationDto.UpperLocationResultDto> data = upperLocationService.getStorage();
        return ResponseEntity.ok().body(responseData.ResultListData(data, ResponseMessage.SUCCESS.name()));
    }

    /**
     * ==============================================
     * <p> 재고내역 상세조회
     * ==============================================
     * user : akfur
     * date : 2023-07-06
     *
     * @param upLocationName
     * @return ResponseEntity<ResultDataList>
     */
    @GetMapping(path = "/search/detail")
    private ResponseEntity<ResultDataList> searchDetailStr(@RequestParam String upLocationName) {
        List<InboundResultDto.InboundSaveResultDto> data = inboundService.getDetailStorage(upLocationName);
        return ResponseEntity.ok().body(responseData.ResultListData(data, ResponseMessage.SUCCESS.name()));
    }
}
