package com.zero.barrageserver.common.entity.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/30.
 */
@Data
public class BaseResponseEntity<T> implements Serializable{
    private int errorCode;
    private String errorMsg;
    private T data;
    public BaseResponseEntity(){}
    public BaseResponseEntity(int errorCode){
        this.errorCode = errorCode;
    }
    public BaseResponseEntity(int errorCode, T data){
        this.errorCode = errorCode;
        this.data = data;
    }
}
