package com.wms.inwms.util.fileUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.inwms.domain.returnOrder.excelMap.ReturnExMap;
import com.wms.inwms.util.MessageUtil;
import com.wms.inwms.util.customException.CustomException;
import com.wms.inwms.util.customException.CustomRunException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
public class FileCommonServiceImpl implements FileService {

    private final FileUtil fileUtil;
    private final ObjectMapper objectMapper;
    private final MessageUtil messageUtil;

    public <T> List<T> readFile(MultipartFile file, String exType) {
        try {
            //aop로 처리하기
            if (file.getSize() >= 100000) throw new CustomRunException(messageUtil.getMessage("ExcelFileSizeMax"));

            fileUtil.fileTypeCheck(file.getOriginalFilename());
            Iterator<Row> rowIterator = getRowIterator(file);

            return objectMapper.convertValue(getReturnOrderListExData(rowIterator), ArrayList.class);
        } catch (CustomException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private List<Map<String, Object>> getReturnOrderListExData(Iterator<Row> rowIterator) {
        List<Map<String, Object>> resultData = new ArrayList<>();
        while (rowIterator.hasNext()) {
            Map<String, Object> excelDataMap = new HashMap<>();
            Iterator<Cell> cellIterator = rowIterator.next().cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case NUMERIC: {
                        String cellData = String.valueOf(cell.getNumericCellValue());
                        excelDataMap.put(ReturnExMap.ReturnOrderExcel.get(cell.getColumnIndex()).get("column"), Double.parseDouble(cellData));
                        break;
                    }
                    default:
                        excelDataMap.put(ReturnExMap.ReturnOrderExcel.get(cell.getColumnIndex()).get("column"), cell.getStringCellValue());
                        break;
                }
            }
            resultData.add(excelDataMap);
        }
        return resultData;
    }

    @NotNull
    private Iterator<Row> getRowIterator(MultipartFile file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        return rowIterator;
    }
}
