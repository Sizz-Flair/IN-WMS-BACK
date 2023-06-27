package com.wms.inwms.controller;

import com.wms.inwms.domain.mapper.cj.CJTrackingDto;
import com.wms.inwms.domain.mapper.cj.CjMapper;
import com.wms.inwms.domain.response.ResponseData;
import com.wms.inwms.domain.response.ResponseMessage;
import com.wms.inwms.domain.response.ResultDataList;
import com.wms.inwms.domain.response.ResultPageData;
import com.wms.inwms.domain.returnOrder.ReturnEntity;
import com.wms.inwms.domain.returnOrder.ReturnService;
import com.wms.inwms.domain.returnOrder.dto.ReturnOrderDto;
import com.wms.inwms.domain.returnOrder.dto.ReturnOrderDtoM;
import com.wms.inwms.domain.returnOrder.dto.ReturnOrderSaveDto;
import com.wms.inwms.util.fileUtil.FileCommonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequiredArgsConstructor
public class ReturnOrderController {

    private final FileCommonServiceImpl fileCommonService;
    private final ReturnService returnService;
    private final ResponseData responseData;
    private final CjMapper cjMapper;

    /**
     * ==============================================
     * <p> EXCEL 파일 받아서 행 검사 후 반환
     * ==============================================
     * user : akfur
     * date : 2023-04-20
     *
     * @param file
     * @return ResponseEntity<ResultDataList>
     */
    @PostMapping(path = "/excel/return")
    public ResponseEntity<ResultDataList> returnDataCheck(@RequestParam MultipartFile file, @RequestParam String exType) {
        List<ReturnOrderDtoM.ReturnExcelDto> resultData = fileCommonService.readFile(file, exType);
        return ResponseEntity.ok().body(responseData.ResultListData(resultData, ResponseMessage.SUCCESS.name()));
    }

    /**
     * ==============================================
     * <p>
     * ==============================================
     * user : akfur
     * date : 2023-04-20
     *
     * @param returnDataList
     * @return ResponseEntity<ResultDataList>
     */
    @PostMapping(path = "/return/save")
    public ResponseEntity<ResultDataList> returnDataSave(@RequestBody List<@Valid ReturnOrderSaveDto> returnDataList) {
        returnService.saveAll(returnDataList);
        return ResponseEntity.ok().body(responseData.ResultListData(new ArrayList<>(), ResponseMessage.SUCCESS.name()));
    }

    /**
     * ==============================================
     * <p>
     * ==============================================
     * user : akfur
     * date : 2023-04-28
     *
     * @param pageable
     * @return ResponseEntity<ResultDataList>
     */
    @PostMapping(path = "/find/return")
    public ResponseEntity<ResultDataList> findData(Pageable pageable) {
        Page<ReturnEntity> data = returnService.find();
        return null;
    }

    /**
     * ==============================================
     * <p> 서치 데이터로 검색 서치 정보 없을 경우 조건 없이 검색
     * ==============================================
     * user : akfur
     * date : 2023-06-20
     *
     * @param searchDto
     * @return ResponseEntity<ResultPageData>
     */
    @GetMapping(path = "/return/search")
    public ResponseEntity<ResultPageData> searchReturnDataPage(@ModelAttribute ReturnOrderDtoM.ReturnOrderSearchDto searchDto) {
        Page<ReturnOrderDtoM.ReturnSaveDto> data = returnService.searchReturnData(searchDto);
        return ResponseEntity.ok().body(responseData.ResultPageData(data, ResponseMessage.SUCCESS.name()));
    }

    /**
     * ==============================================
     * <p> CJ 반품 프로세스(반품신고)
     * ==============================================
     * user : akfur
     * date : 2023-04-20
     *
     * @return ResponseEntity<ResultDataList>
     */
    @PostMapping(path = "/return/report/cj")
    public ResponseEntity<ResultDataList> reportCJ(@RequestBody List<@Valid ReturnOrderDto> returnOrderDtoList) throws Exception {
        List<ReturnOrderDtoM.ReturnSaveDto> data = returnService.shippingReportCJ(returnOrderDtoList);
        return ResponseEntity.ok().body(responseData.ResultListData(data, ResponseMessage.SUCCESS.name()));
    }

    /**
     * ==============================================
     * <p> CJ 배송조회 - CJDB 이용
     * ==============================================
     * user : akfur
     * date : 2023-06-27
     *
     * @param searchData
     * @return ResponseEntity<ResultDataList>
     */
    @PostMapping(path = "/return/search/tracking")
    public ResponseEntity<ResultDataList> trackingCJ(@RequestBody CJTrackingDto searchData) {
        List<Map<String, String>> data = returnService.searchCreated(searchData);
        return ResponseEntity.ok().body(responseData.ResultListData(data, ResponseMessage.SUCCESS.name()));
    }
}
