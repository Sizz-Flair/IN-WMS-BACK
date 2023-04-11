package com.wms.inwms.util.fileUtil;

import com.wms.inwms.domain.returnOrder.Return;
import com.wms.inwms.util.customException.CustomException;
import com.wms.inwms.util.fileUtil.fileDto.DataDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ReturnFileServiceImpl implements FileService {

    private final FileUtil fileUtil;

    public List<Map<String, String>> readFile(File file) throws IOException, CustomException {
        try{
            if(fileUtil.fileTypeCheck(file.getName())) throw new CustomException("Invalid file type: " + file.getName());

            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            Optional<List<Map<String, String>>> dataCheck = Optional.of(checkDataValidation(rowIterator));
            List<Map<String, String>> resultData = getMaps(rowIterator, dataCheck);

            return resultData;
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private List<Map<String, String>> getMaps(Iterator<Row> rowIterator, Optional<List<Map<String, String>>> dataCheck) {
        List<Map<String, String>> returnData = dataCheck.get().isEmpty() ? putValues(rowIterator):dataCheck.get();
        return returnData;
    }

    private List<Map<String, String>> checkDataValidation(Iterator<Row> rowIterator) {
        List<Map<String, String>> resultListData = new ArrayList<>();

        Map<Integer, String> titleStringValues = titleStringValues(rowIterator);


        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case BLANK:
                        resultListData.add(Map.of( , "행/" + row.getRowNum() +" "+ titleStringValues.get(cell.getColumnIndex()) + " 값지 존재하지 않습니다"));
                        break;
                    default: resultListData.add()
                }
            }
        }
        return resultListData;
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
                    row.getCell(titleValues.get(DataDto.ReturnTitleEnum.NUMBER.getValue())).getStringCellValue()));
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
}
