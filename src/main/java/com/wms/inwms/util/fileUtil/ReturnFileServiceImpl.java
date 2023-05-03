package com.wms.inwms.util.fileUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.inwms.util.MessageUtil;
import com.wms.inwms.util.customException.CustomException;
import com.wms.inwms.util.customException.CustomRunException;
import com.wms.inwms.util.fileUtil.fileDto.DataDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
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

    public <T> List<T> readFile(MultipartFile file, Class<T> classType) {
        try {
            fileUtil.fileTypeCheck(file.getOriginalFilename());

            Iterator<Row> rowIterator = getRowData(file);

            Optional<List<Map<String, String>>> dataCheck = Optional.of(checkDataValidation(rowIterator));
            dataCheck.orElseThrow(() -> new CustomException(""));

            List<T> resultDataList = convert(dataCheck.get());

            Boolean.valueOf(true);

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
     * <p>
     * ==============================================
     * user : akfur
     * date : 2023-04-20
     *
     * @param file
     * @return Iterator<Row>
     * @throws IOException
     */
    private Iterator<Row> getRowData(MultipartFile file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        return rowIterator;
    }


    private List<Map<String, String>> checkDataValidation(Iterator<Row> rowIterator) {
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
                        dataCheck(titleStringValues.get(cell.getStringCellValue()), "{DataEmpty}", resultData);
                        break;
                    case _NONE:
                        dataCheck(titleStringValues.get(cell.getStringCellValue()), "{DataEmpty}", resultData);
                        break;
                    case NUMERIC:
                        dataCheck(titleStringValues.get(cell.getColumnIndex()), String.valueOf(cell.getNumericCellValue()), resultData);
                        break;
                    case ERROR:
                        dataCheck(titleStringValues.get(cell.getStringCellValue()), "{DataEmpty}", resultData);
                        break;
                    default:
                        dataCheck(titleStringValues.get(cell.getColumnIndex()), cell.getStringCellValue(), resultData);
                }
            }
            resultListData.add(resultData);
        }
        return resultListData;
    }

    public void dataCheck(String columnName, String data, Map map) {
        if (DataDto.ReturnTitleEnum.NO.getValue().equals(columnName)) map.put("No", data);
        if (DataDto.ReturnTitleEnum.NUMBER.getValue().equals(columnName)) map.put("number", data);
        if (DataDto.ReturnTitleEnum.ORIGIN_NUMBER.getValue().equals(columnName)) map.put("originNumber", data);
        if (DataDto.ReturnTitleEnum.DELIVERY_CODE.getValue().equals(columnName)) map.put("deliveryCode", data);
        if (DataDto.ReturnTitleEnum.UPPER_LOCATION.getValue().equals(columnName)) map.put("upperLocation", data);
        if (DataDto.ReturnTitleEnum.LOWER_LOCATION.getValue().equals(columnName)) map.put("lowerLocation", data);
        if (DataDto.ReturnTitleEnum.AGENT_NAME.getValue().equals(columnName)) map.put("agentName", data);
        if (DataDto.ReturnTitleEnum.RETURN_DATE_TIME.getValue().equals(columnName)) map.put("returnDateTime", data);
        if (DataDto.ReturnTitleEnum.AIR_OCEAN_TYPE.getValue().equals(columnName)) map.put("ariOceanType", data);
        if (DataDto.ReturnTitleEnum.ETC_MEMO.getValue().equals(columnName)) map.put("etcMemo", data);
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
