package com.wms.inwms.util.fileUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.inwms.util.customException.CustomException;
import com.wms.inwms.util.fileUtil.fileDto.DataDto;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@RequiredArgsConstructor
@Service
public class ReturnFileServiceImpl implements FileService {

    private final FileUtil fileUtil;
    private final ObjectMapper objectMapper;

public <T> List<T> readFile(MultipartFile file, Class<T> classType) throws IOException, CustomException {
        try{
            fileUtil.fileTypeCheck(file.getOriginalFilename());

            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            Optional<List<Map<String, String>>> dataCheck = Optional.of(checkDataValidation(rowIterator));
            dataCheck.orElseThrow(() -> new CustomException(""));

            List<T>resultDataList = convert(dataCheck.get());

            return resultDataList;
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }
    //List<Return> resultReturnData = objectMapper.convertValue(resultData, ArrayList.class);

//    private List<Map<String, String>> getMaps(Iterator<Row> rowIterator, Optional<List<Map<String, String>>> dataCheck) {
//        List<Map<String, String>> returnData = dataCheck.get().isEmpty() ? putValues(rowIterator):dataCheck.get();
//        return returnData;
//    }

    private List<Map<String, String>> checkDataValidation(Iterator<Row> rowIterator) {
        List<Map<String, String>> resultListData = new ArrayList<>();
        Map<Integer, String> titleStringValues = titleStringValues(rowIterator);

        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Map<String, String> resultData = new HashMap<>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case BLANK:
                        dataCheck(titleStringValues.get(cell.getStringCellValue()), "값이 없습니다.", resultData);
                        break;
                    default: dataCheck(titleStringValues.get(cell.getColumnIndex()),cell.getStringCellValue(), resultData);
                }
            }
            resultListData.add(resultData);
        }
        return resultListData;
    }

    public void dataCheck(String columnName, String data, Map map) {
        try{
            if(DataDto.ReturnTitleEnum.NO.getValue().equals(columnName)) map.put("number",data);
            if(DataDto.ReturnTitleEnum.NUMBER.getValue().equals(columnName)) map.put("number",data);
            if(DataDto.ReturnTitleEnum.ORIGIN_NUMBER.getValue().equals(columnName)) map.put("originNumber",data);
            if(DataDto.ReturnTitleEnum.DELIVERY_CODE.getValue().equals(columnName)) map.put("deliveryCode",data);
            if(DataDto.ReturnTitleEnum.UPPER_LOCATION.getValue().equals(columnName)) map.put("upperLocation",data);
            if(DataDto.ReturnTitleEnum.LOWER_LOCATION.getValue().equals(columnName)) map.put("lowerLocation",data);
            if(DataDto.ReturnTitleEnum.AGENT_NAME.getValue().equals(columnName)) map.put("agentName",data);
            if(DataDto.ReturnTitleEnum.RETURN_DATE_TIME.getValue().equals(columnName)) map.put("returnDateTime",data);
            if(DataDto.ReturnTitleEnum.AIR_OCEAN_TYPE.getValue().equals(columnName)) map.put("ariOceanType",data);
            if(DataDto.ReturnTitleEnum.ETC_MEMO.getValue().equals(columnName)) map.put("etcMemo",data);
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("엑셀 양식과 다릅니다.");
        }
    }
//    Return returnData =
//            Return
//                    .builder()
//                    .number(row.getCell(titleValues.get(DataDto.ReturnEnum.NUMBER.getValue())).getStringCellValue())
//                    .originNumber(row.getCell(titleValues.get(DataDto.ReturnEnum.ORIGIN_NUMBER.getValue())).getStringCellValue())
//                    .deliveryCom(row.getCell(titleValues.get(DataDto.ReturnEnum.DELIVERY_CODE.getValue())).getStringCellValue())
//                    .agency(row.getCell(titleValues.get(DataDto.ReturnEnum.AGENT_NAME.getValue())).getStringCellValue())
//                    .build();

    private List<Map<String, String>> putValues(Iterator<Row> rowIterator) {
        Map<String, Integer> titleValues = titleSeq(rowIterator);
        List<Map<String, String>> resultDataList = new ArrayList<>();

        while(rowIterator.hasNext()) {
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

        while(cellTitle.hasNext()) {
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

        while(cellTitle.hasNext()) {
            Cell cell = cellTitle.next();
            Integer cellNumber = cell.getColumnIndex();
            firstRowMap.put(cellNumber, cell.getStringCellValue());
        }
        return firstRowMap;
    }

    private <T>List<T> convert(List<Map<String, String>> data) {
        List<T> resultData = objectMapper.convertValue(data, ArrayList.class);
        return resultData;
    }
}
