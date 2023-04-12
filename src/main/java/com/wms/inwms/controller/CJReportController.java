package com.wms.inwms.controller;

import com.wms.inwms.domain.cj.CJDto;
import com.wms.inwms.domain.cj.CJService;
import com.wms.inwms.domain.cj.CjAddress;
import com.wms.inwms.util.ExcelUtil;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wms.inwms.util.fileUtil.FileService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.json.simple.JSONObject;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CJReportController implements ErrorController {
    private final CJService cjService;

    private final ExcelUtil excelUtil;
    private final Map<String, FileService> fileServiceMap;

    public CJReportController(CJService cjService, ExcelUtil excelUtil, Map<String, FileService> fileServiceMap) {
        this.cjService = cjService;
        this.excelUtil = excelUtil;
        this.fileServiceMap = fileServiceMap;
    }

    @PostMapping({"/test"})
    public String testController() {
        return "Test";
    }

    @PostMapping({"/find"})
    public List<CJDto> find() throws Exception {
        String version = org.springframework.core.SpringVersion.getVersion();
        System.out.println(version);
        return this.cjService.findAll();
    }

    @PostMapping({"/xlsx"})
    public List<CJDto> excel(@RequestParam MultipartFile file) throws Exception {
        List<CJDto> cjDtoList = new ArrayList<>();
        try {
            if (this.excelUtil.excelFileCheck(file)) {
                HSSFWorkbook hss = new HSSFWorkbook(file.getInputStream());
                HSSFSheet hsSheet = hss.getSheetAt(0);
                Map<String, Integer> colIndex = extracted(hsSheet);
                for (int i = 1; i < hsSheet.getPhysicalNumberOfRows(); i++) {
                    JSONObject zipCode = CjAddress.getAddress(String.valueOf(hsSheet.getRow(i).getCell(((Integer)colIndex.get("gngNumber")).intValue())),
                            String.valueOf(hsSheet.getRow(i).getCell(((Integer)colIndex.get("consigneeAddress")).intValue())));
                    Map<Object, Object> testMAP = new HashMap<>();
                    for (int idx = 1; idx < 16; idx++)
                        testMAP.put("\"printLabel" + idx + "\"", String.valueOf("\"" + zipCode.get("PRINT_LABEL" + idx) + "\""));
                    CJDto cjDto = CJDto.builder()
                            .consigneeNameImp(String.valueOf(hsSheet.getRow(i).getCell(((Integer)colIndex.get("consigneeName")).intValue()))).consigneeTel(String.valueOf(hsSheet.getRow(i).getCell(((Integer)colIndex.get("consigneeTel")).intValue())))
                            .consigneeAddressImp(String.valueOf(hsSheet.getRow(i).getCell(((Integer)colIndex.get("consigneeAddress")).intValue()))).gngNumber(String.valueOf(hsSheet.getRow(i).getCell(((Integer)colIndex.get("gngNumber")).intValue()))).consigneeZip(String.valueOf(hsSheet.getRow(i).getCell(((Integer)colIndex.get("consigneeZip")).intValue()))).hwbNo(String.valueOf(hsSheet.getRow(i).getCell(((Integer)colIndex.get("cjNumber")).intValue())).replace("-", "")).printData(String.valueOf(testMAP).replace("=", ":")).orderNo(String.valueOf(hsSheet.getRow(i).getCell(((Integer)colIndex.get("orderNo")).intValue()))).createdCj(Instant.now(Clock.systemUTC())).build();
                    cjDtoList.add(cjDto);
                }
                this.cjService.saveAll(cjDtoList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cjDtoList;
    }

    private Map<String, Integer> extracted(HSSFSheet hsSheet) {
        HSSFRow hSSFRow = hsSheet.getRow(0);
        Map<Object, Object> map = new HashMap<>();
        for (Cell cell : hSSFRow) {
            switch (cell.getStringCellValue()) {
                case "NO":
                    map.put("orderNo", Integer.valueOf(cell.getColumnIndex()));
                case "집화혜정일자":
                    map.put("createdCj", Integer.valueOf(cell.getColumnIndex()));
                case "보내는분":
                    map.put("shipperName", Integer.valueOf(cell.getColumnIndex()));
                case "받는분":
                    map.put("consigneeName", Integer.valueOf(cell.getColumnIndex()));
                case "받는분 전화번호":
                    map.put("consigneeTel", Integer.valueOf(cell.getColumnIndex()));
                case "받는분 우편번호":
                    map.put("consigneeZip", Integer.valueOf(cell.getColumnIndex()));
                case "받는분주소":
                    map.put("consigneeAddress", Integer.valueOf(cell.getColumnIndex()));
                case "상품명":
                    map.put("gngNumber", Integer.valueOf(cell.getColumnIndex()));
                case "운송장번호":
                    map.put("cjNumber", Integer.valueOf(cell.getColumnIndex()));
            }
        }
        return (Map)map;
    }
}