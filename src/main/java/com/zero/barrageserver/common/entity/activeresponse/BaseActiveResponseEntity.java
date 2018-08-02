package com.zero.barrageserver.common.entity.activeresponse;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/30.
 */
@Data
public class BaseActiveResponseEntity<T> implements Serializable {
    private String result;
    private String error;
    private T data;
    public BaseActiveResponseEntity(){}
    public BaseActiveResponseEntity(String result, T data){
        this.result = result;
        this.data = data;
    }
}
