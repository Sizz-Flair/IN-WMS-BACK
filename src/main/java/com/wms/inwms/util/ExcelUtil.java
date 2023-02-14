package com.wms.inwms.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ExcelUtil {
    public boolean excelFileCheck(MultipartFile file) {
        if (file.getSize() <= 0L)
            return false;
        if (file.getOriginalFilename().contains(".xls") || file.getOriginalFilename().contains(".xlsx"))
            return true;
        return false;
    }
}
