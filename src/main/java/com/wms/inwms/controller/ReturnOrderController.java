package com.wms.inwms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.inwms.domain.response.ResponseData;
import com.wms.inwms.domain.response.ResultData;
import com.wms.inwms.domain.response.ResultDataList;
import com.wms.inwms.domain.returnOrder.Return;
import com.wms.inwms.domain.returnOrder.ReturnOrderDto;
import com.wms.inwms.domain.returnOrder.ReturnRepository;
import com.wms.inwms.domain.returnOrder.ReturnService;
import com.wms.inwms.util.customException.CustomException;
import com.wms.inwms.util.fileUtil.ReturnFileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Validated
public class ReturnOrderController {

    private final ReturnFileServiceImpl returnFileService;
    private final ReturnService returnService;
    private final ResponseData responseData;
    private final ObjectMapper objectMapper;

    //@RequestParam MultipartFile file
    @PostMapping(path="/return")
    public ResponseEntity<ResultDataList> returnDataCheck(@RequestParam MultipartFile file) throws IOException, CustomException {
        List<Map<String, String>> resultData = returnFileService.readFile(file);
        List<Return> resultReturnData = objectMapper.convertValue(resultData, ArrayList.class);

        return ResponseEntity.ok().body(responseData.ResultListData(resultReturnData,"SUCCESS"));
    }

    @PostMapping(path="/return/save")
    public ResponseEntity<ResultData> returnDataSave(@RequestBody List<@Valid ReturnOrderDto> returnDataList)  {


        System.out.println("?");
        return null;
    }
}
