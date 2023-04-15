package com.wms.inwms.util.fileUtil;

import com.wms.inwms.util.customException.CustomException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileService {
    <T>List<T> readFile(MultipartFile file, Class<T> classType) throws IOException, CustomException;
}
