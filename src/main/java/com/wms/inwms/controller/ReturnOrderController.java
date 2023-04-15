package com.wms.inwms.controller;

import com.wms.inwms.domain.response.ResponseData;
import com.wms.inwms.domain.response.ResultDataList;
import com.wms.inwms.domain.returnOrder.Return;
import com.wms.inwms.domain.returnOrder.ReturnService;
import com.wms.inwms.util.customException.CustomException;
import com.wms.inwms.util.fileUtil.ReturnFileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ReturnOrderController {

    private final ReturnFileServiceImpl returnFileService;
    private final ReturnService returnService;
    private final ResponseData responseData;

    @PostMapping(path="/return")
    public ResponseEntity<ResultDataList> returnDataCheck(@RequestParam MultipartFile file) throws IOException, CustomException {
        List<Return> resultData = returnFileService.readFile(file, Return.class);
        return ResponseEntity.ok().body(responseData.ResultListData(resultData,"SUCCESS"));
    }

    @PostMapping(path="/return/save")
    public ResponseEntity<ResultDataList> returnDataSave(@RequestBody List<@Valid Return> returnDataList)  {
        List<Return> resultDataList = returnService.saveAll(returnDataList);
        return ResponseEntity.ok().body(responseData.ResultListData(resultDataList, "SUCCESS"));
    }
}
