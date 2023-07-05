package com.wms.inwms.controller;

import com.wms.inwms.domain.inbound.InboundService;
import com.wms.inwms.domain.inbound.dto.InboundResultDto;
import com.wms.inwms.domain.inbound.dto.InboundSaveDto;
import com.wms.inwms.domain.response.ResponseData;
import com.wms.inwms.domain.response.ResponseMessage;
import com.wms.inwms.domain.response.ResultDataList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
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

    /**
     * ==============================================
     * <p> 입고데이터 저장 및 맵핑
     * ==============================================
     * user : akfur
     * date : 2023-06-15
     *
     * @param saveDto
     * @param up
     * @param low
     * @return ResponseEntity<ResultDataList>
     */
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

    /**
     * ==============================================
     * <p> 검색일자로 맵핑된 입고데이터 조회
     * ==============================================
     * user : akfur
     * date : 2023-06-15
     *
     * @param startDate
     * @param endDate
     * @return ResponseEntity<ResultDataList>
     */
    @GetMapping(path = "/search/mapping")
    private ResponseEntity<ResultDataList> findMappingHwb(@RequestParam Instant startDate, @RequestParam Instant endDate) {
        List<InboundResultDto.InboundMappingResultDto> resultData = inboundService.findByMappingToDate(startDate, endDate);
        return ResponseEntity.ok().body(responseData.ResultListData(resultData, ResponseMessage.SUCCESS.name()));
    }

    /**
     * ==============================================
     * <p> 입고 맵핑데이터 조회
     * ==============================================
     * user : akfur
     * date : 2023-06-15
     *
     * @param mappingNum
     * @return ResponseEntity<ResultDataList>
     */
    @GetMapping(path = "/select/mappig")
    private ResponseEntity<ResultDataList> selectMapping(@RequestParam String mappingNum) {
        List<InboundResultDto.InboundSelectResultDto> resultData = inboundService.getSelectInboundData(mappingNum);
        return ResponseEntity.ok().body(responseData.ResultListData(resultData, ResponseMessage.SUCCESS.name()));
    }

    /**
     * ==============================================
     * <p> 재고내역 조회(상위 로케이션)
     * ==============================================
     * user : akfur
     * date : 2023-06-28
     *
     * @return ResponseEntity<ResultDataList>
     */
    @GetMapping(path = "/serach/storage")
    private ResponseEntity<ResultDataList> searchStorage() {
        List<UpperLocationDto.UpperLocationResultDto> data = inboundService.getStorage();
        return ResponseEntity.ok().body(responseData.ResultListData(data, ResponseMessage.SUCCESS.name()));
    }

    /**
     * ==============================================
     * <p> 재고내역 상세 조회
     * ==============================================
     * user : akfur
     * date : 2023-06-28
     *
     * @param locationName
     * @return ResponseEntity<ResultDataList>
     */
    @GetMapping(path = "/search/storage/detail")
    private ResponseEntity<ResultDataList> searchDetailStr(@RequestParam String locationName) {
        List<InboundResultDto.InboundSaveResultDto> data = inboundService.getDetailStorage(locationName);
        return ResponseEntity.ok().body(responseData.ResultListData(data, ResponseMessage.SUCCESS.name()));
    }
}
