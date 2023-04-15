package com.wms.inwms.util.fileUtil;

import com.wms.inwms.util.customException.CustomException;
import org.springframework.stereotype.Component;

@Component
public class FileUtil {
    public void fileTypeCheck(String fileName) throws CustomException {
        if(!(fileName.contains("xlsx") || fileName.contains("xls"))) throw new CustomException("파일형식이 다릅니다.");
    }
}
