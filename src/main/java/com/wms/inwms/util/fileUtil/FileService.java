package com.wms.inwms.util.fileUtil;

import com.wms.inwms.util.customException.CustomException;
import com.wms.inwms.util.fileUtil.fileDto.DataDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileService {
    <T>List<T> readFile(MultipartFile file, String exType) throws IOException, CustomException;
}
