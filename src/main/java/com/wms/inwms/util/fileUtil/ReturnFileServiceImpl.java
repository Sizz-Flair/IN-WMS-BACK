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

            List<Map<String, String>> returnData = dataCheck.orElseGet(() -> {
                return null;
            });

            //orElseThrow(() -> new CustomException("데이터가 없습니다."));

            return returnData;
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private List<Map<String, String>> checkDataValidation(Iterator<Row> rowIterator) {
        List<Map<String, String>> resultListData = new ArrayList<>();

        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case BLANK:
                        resultListData.add(Map.of("error", "행/" + row.getRowNum() + " 열/" + cell.getColumnIndex() + " 값지 존재하지 않습니다"));
                        break;
                }
            }
        }
        return resultListData;
    }

    private List<Map<String, String>> putValues(Iterator<Row> rowIterator) {
        Row row = rowIterator.
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
