package com.wms.inwms.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResultData<T> {

    public ResultData(String message) {
        this.message = message;
    }

    public ResultData(T data, String message) {
        this.data = data;
        this.message = message;
    }
    private T data;
    private String message;
}
