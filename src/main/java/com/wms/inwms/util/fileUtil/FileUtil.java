package com.wms.inwms.util.fileUtil;

import org.springframework.stereotype.Component;

@Component
public class FileUtil {
    public boolean fileTypeCheck(String fileName) {
        return (!(fileName.contains("xlsx") || fileName.contains("xls")));
    }
}
