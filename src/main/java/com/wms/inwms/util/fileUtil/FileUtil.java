package com.wms.inwms.util.fileUtil;

public class FileUtil {
    public boolean fileTypeCheck(String fileName) {
        return (fileName.contains("xlsx") || fileName.contains("xls"));
    }
}
