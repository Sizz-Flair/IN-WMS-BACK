package com.wms.inwms.util.fileUtil;

import com.wms.inwms.domain.returnOrder.Return;
import com.wms.inwms.util.customException.CustomException;
import com.wms.inwms.util.fileUtil.fileDto.DataDto;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
public class ReturnFileServiceImpl implements FileService {

    private final FileUtil fileUtil;
    private final DataDto dataDto;

    public Map<String, String> readFile(File file) {
        Map<String, String> returnData = new HashMap<>();
        try{
            if(fileUtil.fileTypeCheck(file.getName())) throw new CustomException("Invalid file type: " + file.getName());

            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            Optional<Map<String, String>> dataCheck = Optional.of(checkDataVaildation(rowIterator));
            returnData = dataCheck.orElseGet(() -> new HashMap<>());
            return returnData;
        } catch(IOException e) {
            returnData = Map.of("error", "등록오류 발생");
            return returnData;
        } catch (CustomException e) {
            returnData = Map.of("error", "파일타입이 맞지 않습니다.");
            return returnData;
        }
    }


    //Map<String ,Integer> titleData = titleSeq(rowIterator);

//                Return.builder()
//                        .number(row.getCell(titleData.get(DataDto.ReturnEnum.NUMBER.getValue())).getStringCellValue())
//                        .originNumber(row.getCell(titleData.get(DataDto.ReturnEnum.ORIGIN_NUMBER.getValue())).getStringCellValue())
//                        .deliveryCom(row.getCell(titleData.get(DataDto.ReturnEnum.DELIVERY_CODE.getValue())).getStringCellValue())

    private Map<String, String> checkDataVaildation(Iterator<Row> rowIterator) {
        Map<String, String> errorMsg = new HashMap<>();
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case BLANK:
                        errorMsg.put("error", "행/" + row.getRowNum() + " 열/" + cell.getColumnIndex() + " 값지 존재하지 않습니다");
                        break;
                }
            }
        }
        return errorMsg;
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
}
