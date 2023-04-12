package com.wms.inwms.domain.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ResultDataList<T>{
    private List<T> listData;
    private String message;
    public ResultDataList(List<T> listData,String message) {
        this.listData = listData;
        this.message = message;
    }
}
