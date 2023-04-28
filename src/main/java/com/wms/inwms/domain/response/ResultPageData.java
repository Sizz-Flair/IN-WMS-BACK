package com.wms.inwms.domain.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class ResultPageData<T> {
    private Page<T> listData;
    private String message;
    public ResultPageData(Page<T> listData,String message) {
        this.listData = listData;
        this.message = message;
    }
}
