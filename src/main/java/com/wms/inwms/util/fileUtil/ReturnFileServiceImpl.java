package com.wms.inwms.util.fileUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.inwms.domain.returnOrder.dto.ReturnOrderDtoM;
import com.wms.inwms.domain.returnOrder.excelMap.ReturnExMap;
import com.wms.inwms.util.MessageUtil;
import com.wms.inwms.util.customException.CustomException;
import com.wms.inwms.util.customException.CustomRunException;
import com.wms.inwms.util.fileUtil.fileDto.DataDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@RequiredArgsConstructor
@Service
@Slf4j
public class ReturnFileServiceImpl implements FileService {

    private final FileUtil fileUtil;
    private final ObjectMapper objectMapper;
    private final MessageUtil messageUtil;

    /**
     * ==============================================
     * <p> excel data 검증 후 반환
     * ==============================================
     * user : akfur
     * date : 2023-06-08
     *
     * @param file
     * @param exType
     * @param <T>
     * @return List<T>
     */
    public <T> List<T> readFile2(MultipartFile file, String exType) {
        //commoncode가 있다고 가정

        try {
            fileUtil.fileTypeCheck(file.getOriginalFilename());

            Iterator<Row> rowIterator = getRowIterator(file);
            return getReturnOrderListExData(rowIterator);
        } catch (CustomException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private <T>List<T> getReturnOrderListExData(Iterator<Row> rowIterator) {
        Map<String, Object> excelDataMap = new HashMap<>();
        List<ReturnOrderDtoM.ReturnExcelDto> resultListData = new ArrayList<>();
        while(rowIterator.hasNext()) {
            Iterator<Cell> cellIterator = rowIterator.next().cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case NUMERIC: {
                        String cellData = String.valueOf(cell.getNumericCellValue());
                        excelDataMap.put(ReturnExMap.ReturnOrderExcel.get(cell.getColumnIndex()).get("column"), Double.parseDouble(cellData)); break;
                    }
                    default: excelDataMap.put(ReturnExMap.ReturnOrderExcel.get(cell.getColumnIndex()).get("column"), cell.getStringCellValue()); break;
                }
            }
            ReturnOrderDtoM.ReturnExcelDto getDto = objectMapper.convertValue(excelDataMap, ReturnOrderDtoM.ReturnExcelDto.class);
            resultListData.add(getDto);
        }
    }

    @NotNull
    private Iterator<Row> getRowIterator(MultipartFile file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        return rowIterator;
    }

    /**
     * ==============================================
     * <p> excel 데이터 조회후 반환
     * ==============================================
     * user : akfur
     * date : 2023-05-04
     *
     * @param file
     * @param exType
     * @return List<T>
     *
     * exType조회 후 그 타입에 맞는 Map데이터를 반환 후 데이터 체크
     */
    public <T> List<T> readFile(MultipartFile file, String exType) {
        try {
            fileUtil.fileTypeCheck(file.getOriginalFilename());
            Map<String, String> excelData = selectExcelType(exType);

            Iterator<Row> rowIterator = getRowData(file);

            Optional<List<Map<String, String>>> dataCheck = Optional.of(checkDataValidation(rowIterator, excelData));
            dataCheck.orElseThrow(() -> new CustomException(""));

            List<T> resultDataList = convert(dataCheck.get());

            return resultDataList;
        } catch (CustomException e) {
            log.error("CustomException Error", e.getMessage(), e);
            throw new CustomRunException("");
        } catch (IOException e) {
            log.error("IOException Error", e.getMessage(), e);
            throw new CustomRunException("");
        } catch (IllegalArgumentException e) {
            log.error("Argument Error: {}", e.getMessage(), e);
            throw new CustomRunException(messageUtil.getMessage("ExcelDataNull"));
        } catch (NoSuchElementException e) {
            log.error("No Such element: {}", e.getMessage(), e);
            throw new CustomRunException(messageUtil.getMessage("ExcelDataNull"));
        }
    }

    /**
     * ==============================================
     * <p> exType에 맞는 Map을 반환
     * ==============================================
     * user : akfur
     * date : 2023-05-04
     *
     * @param exType
     * @return Map<String, String>
     *
     * 정적 메소드 방식으로 Map작성, Map.of immutable처리
     */
    private Map<String, String> selectExcelType(String exType) {
        Map<String, String> excelSelect = null;
        switch (exType) {
            case "ReturnOrder": excelSelect = ReturnExMap.getReturnOrderEx();
        }
        Optional<Map<String, String>> optMap = Optional.ofNullable(excelSelect);
        optMap.orElseThrow(() -> new CustomRunException("{ExcelTypeMiss}"));
        return excelSelect;
    }

    /**
     * ==============================================
     * <p> Excel 행 데이터 가져오기
     * ==============================================
     * user : akfur
     * date : 2023-04-20
     *
     * @param file
     * @return Iterator<Row>
     * @throws IOException
     *
     * Iterator<Row> 받아오며 Sheet는 무조건 0에서 받아옴 (추후 주가 시트 진행은 확인 후 진행)
     */
    private Iterator<Row> getRowData(MultipartFile file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        return rowIterator;
    }

    protected <T extends Map<String, String>> List<Map<String, String>> checkDataValidation(Iterator<Row> rowIterator, T exMap) {
        List<Map<String, String>> resultListData = new ArrayList<>();
        Map<Integer, String> titleStringValues = titleStringValues(rowIterator);
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Map<String, String> resultData = new HashMap<>();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case BLANK:
                        dataCheck(titleStringValues.get(cell.getStringCellValue()), "{DataEmpty}", resultData, exMap);
                        break;
                    case _NONE:
                        dataCheck(titleStringValues.get(cell.getStringCellValue()), "{DataEmpty}", resultData, exMap);
                        break;
                    case NUMERIC:
                        dataCheck(titleStringValues.get(cell.getColumnIndex()), String.valueOf(cell.getNumericCellValue()), resultData, exMap);
                        break;
                    case ERROR:
                        dataCheck(titleStringValues.get(cell.getStringCellValue()), "{DataEmpty}", resultData, exMap);
                        break;
                    default:
                        dataCheck(titleStringValues.get(cell.getColumnIndex()), cell.getStringCellValue(), resultData, exMap);
                }
            }
            resultListData.add(resultData);
        }
        return resultListData;
    }

    public void dataCheck(String columnName, String data, Map map, Map<String, String> mapEx) {
        mapEx.forEach((k, v) -> {
            if(v.equals(columnName)) {
                map.put(k,data);
            }
        });
    }

    private List<Map<String, String>> putValues(Iterator<Row> rowIterator) {
        Map<String, Integer> titleValues = titleSeq(rowIterator);
        List<Map<String, String>> resultDataList = new ArrayList<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            resultDataList.add(Map.of(DataDto.ReturnEnum.NUMBER.getValue(),
                    row.getCell(titleValues.get(DataDto.ReturnEnum.NUMBER.getValue())).getStringCellValue()));
        }
        return resultDataList;
    }

    private Map<String, Integer> titleSeq(Iterator<Row> rowIterator) {
        Row firstRow = rowIterator.next();
        Map<String, Integer> firstRowMap = new HashMap<>();
        Iterator<Cell> cellTitle = firstRow.cellIterator();

        while (cellTitle.hasNext()) {
            Cell cell = cellTitle.next();
            Integer cellNumber = cell.getColumnIndex();
            firstRowMap.put(cell.getStringCellValue(), cellNumber);
        }
        return firstRowMap;
    }


    private Map<Integer, String> titleStringValues(Iterator<Row> rowIterator) {
        Row firstRow = rowIterator.next();
        Map<Integer, String> firstRowMap = new HashMap<>();
        Iterator<Cell> cellTitle = firstRow.cellIterator();

        while (cellTitle.hasNext()) {
            Cell cell = cellTitle.next();
            Integer cellNumber = cell.getColumnIndex();
            firstRowMap.put(cellNumber, cell.getStringCellValue());
        }
        return firstRowMap;
    }

    private <T> List<T> convert(List<Map<String, String>> data) {
        List<T> resultData = objectMapper.convertValue(data, ArrayList.class);
        return resultData;
    }
}
