package com.wms.inwms.util.fileUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface FileService<T> {
    Map<String, String> readFile(File file);
}
