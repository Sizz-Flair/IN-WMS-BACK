package com.wms.inwms.domain.response;

import com.wms.inwms.domain.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResponseData {

    public enum MessageCode {
        SUCCESS, FAIL
    }

    public <T extends BaseModel>ResultData ResultData(T data, String message) {
        return new ResultData<T>(data, message);
    }

    public<T extends String>ResultData ResultDataMessage(String message) {
        return new ResultData<T>(message);
    }

    public <T>ResultDataList ResultListData(List<T> listData, String message) {
        return new ResultDataList(listData, message);
    }

    public<T>ResultPageData ResultPageData(Page<T> pageData, String message ) {
        return new ResultPageData(pageData, message);
    }

    public ResultData ErrorResultData(String message) {
        return new ResultData(message,"FAIL");
    }
}
