package com.wms.inwms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.inwms.domain.response.ResponseData;
import com.wms.inwms.domain.response.ResponseMessage;
import com.wms.inwms.domain.response.ResultDataList;
import com.wms.inwms.domain.response.ResultPageData;
import com.wms.inwms.domain.returnOrder.ReturnEntity;
import com.wms.inwms.domain.returnOrder.ReturnService;
import com.wms.inwms.domain.returnOrder.dto.*;
import com.wms.inwms.util.fileUtil.FileCommonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Validated
public class ReturnOrderController {

    //private final ReturnFileServiceImpl returnFileService;
    private final FileCommonServiceImpl fileCommonService;
    private final ReturnService returnService;
    private final ResponseData responseData;

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
        //ObjectMapper ob = new ObjectMapper();
        //List<ReturnEntity> r = returnDataList.stream().map(e -> ob.convertValue(e, ReturnEntity.class)).collect(Collectors.toList());
        List<ReturnOrderSaveDto> resultDataList = returnService.saveAll(returnDataList);
        return ResponseEntity.ok().body(responseData.ResultListData(resultDataList, ResponseMessage.SUCCESS.name()));
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
     * <p>
     * ==============================================
     * user : akfur
     * date : 2023-04-20
     *
     * @param pageable
     * @param searchDto
     * @return ResponseEntity<ResultPageData>
     */
    @GetMapping(path = "/return/search")
    public ResponseEntity<ResultPageData> searchReturnData(Pageable pageable, @ModelAttribute ReturnSearchDto searchDto) {
        Page<ReturnResponseDto> data = returnService.searchReturnData(pageable, searchDto);
        return ResponseEntity.ok().body(responseData.ResultPageData(data, "SUCCESS"));
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
        returnService.shippingReportCJ(returnOrderDtoList);
        return null;
    }

}
