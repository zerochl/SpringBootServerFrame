package com.zero.barrageserver.common.constant;

/**
 * Created by Administrator on 2017/12/4.
 */
public class ApiSupportConstant {
    public static final String API_TEN_PREFIX = "v1/ten";
    public static final String API_CLIENT_PREFIX = "v1/client";
    public static final String API_SERVER_PREFIX = "v1/server";
    public static final String API_CMS_PREFIX = "v1/cms";
    public static final String API_TEST_PREFIX = "v1/test";
    public static final String API_GOLOBAL_PREFIX = "v1/golobal";

    /**Ten相关**************/
    public static final String GET_GROUP_ID = "/groupid";
    /**客户端相关****************/
    public static final String GET_ALL_SHOP_FOR_CHOOSE = "/shops/choose";
    public static final String GET_SHOP_FOR_CHOOSE = "/shop/choose";
    public static final String GET_PROGRAM_INFO = "/im/pull/program";
    public static final String LEAVE_OTHER_GROUP = "/im/group/leaveother";
    public static final String POST_BARRAGE_DEVICE_UPDATE = "/device/update";
    public static final String GET_BOOT_STRAP_DOWNLOAD_FILE = "/download/bootstrap";
    /**Server相关**/
    public static final String PUSH_MESSAGE = "/message/push";
    /**CMS 相关*/
    public static final String SHOP_CHANGE = "/shop/change";
    public static final String ACTIVITY_CHANGE = "/activity/change";
    public static final String GET_GROUP_ONLINE_MEMBERS = "/shop/device/online";
    public static final String SHOP_BARRAGE_CONFIG = "/shop/barrage/config";
    public static final String SHOP_BARRAGE_MOVIE = "/shop/barrage/movie";
    public static final String SHOP_BARRAGE_SHOW_IMAGE = "/shop/barrage/showimage";
    public static final String SHOP_BARRAGE_REST_IMAGE = "/shop/barrage/restimage";
    public static final String HISTORY_MSG_FOR_PAG = "/pad/history";
    /**测试*/
    public static final String TEST_PUSH_MESSAGE = "/message/push";
    /**Golobal*/
    public static final String GOLOBAL_ERROR = "/error";

}
