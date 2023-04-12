package com.wms.inwms.util.fileUtil;

import com.wms.inwms.util.customException.CustomException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileService<T> {
    List<Map<String, String>> readFile(File file) throws IOException, CustomException;
}
