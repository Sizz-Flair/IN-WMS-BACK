package com.wms.inwms.util.fileUtil;

import com.wms.inwms.util.MessageUtil;
import com.wms.inwms.util.customException.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileUtil {
    private final MessageUtil messageUtil;

    public void fileTypeCheck(String fileName) throws CustomException {
        if(!(fileName.contains("xlsx") || fileName.contains("xls")))
            throw new CustomException(messageUtil.getMessage("ExcelTypeError"));
    }
}
