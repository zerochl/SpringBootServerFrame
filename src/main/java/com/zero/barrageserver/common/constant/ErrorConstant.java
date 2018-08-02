package com.zero.barrageserver.common.constant;

/**
 * Created by Administrator on 2017/12/1.
 */
public class ErrorConstant {
    /****************Ten********************/
    public static final int TEN_IM_ERROR_CODE_OK = 0;//返回正确
    public static final int TEN_IM_ERROR_CODE_GROUP_ID_CREATED = 10021;//已经创建过此group id
    /****************My***********************/
    public static final int ERROR_CODE_OK = 1;//请求返回OK
    public static final int ERROR_CODE_OTHER = 200000;//请求返回为null

    public static final int ERROR_CODE_PARAM_ERROR = 200001;//参数不对

    public static final int ERROR_CODE_RESPONSE_NULL = 200002;//请求返回为null
    public static final int ERROR_CODE_RESPONSE_SHOP_NULL = 2000021;//请求返回为null
    public static final int ERROR_CODE_RESPONSE_ACTIVITY_NULL = 2000022;//请求返回为null

    public static final int ERROR_CODE_MSG_UNWANTED = 200003;//此消息不需要

    public static final int ERROR_CODE_ACTIVE_REQUEST = 200004;//主动请求Error
    public static final int ERROR_CODE_ACTIVE_REQUEST_TEN = 2000041;//主动请求Error,腾讯云请求
}
