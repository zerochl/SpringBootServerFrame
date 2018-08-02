package com.zero.barrageserver.common.exception;

/**
 * Created by Administrator on 2017/10/12.
 */

/**
 * 统一异常
 */
public class AnyException extends RuntimeException {

    public AnyException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getMessage());
    }

    public AnyException(String message){
        super(message);
    }

}
