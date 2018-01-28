package cn.tzmedia.barrageserver.common.constant;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/30.
 */
public class ApiConstant {

    public static final String RELEASE_SERVER_HOST = "http://***/";
    public static final String RELEASE_CMS_HOST = "http://***/";
    public static final String RELEASE_TEN_HOST = "https://console.tim.qq.com/";
    public static final String TEST_HOST = "http://127.0.0.1:9001/";

    public static final String QINIU_RELEASE_IMAGE_HOST = "***";
    public static final String QINIU_TEST_IMAGE_HOST = "***";
    public static final ArrayList<String> QINIU_IMAGE_HOST = new ArrayList<String>(){{
        add(QINIU_RELEASE_IMAGE_HOST);
        add(QINIU_TEST_IMAGE_HOST);
    }};
    public static final String RELEASE_IMAGE_HOST = "http://***/";

    public static final String TEN_CREATE_GROUP = "v4/group_open_http_svc/create_group?usersig=" + AccountConstant.TEN_IM_ADMIN_SIG + "&apn=1&identifier=" + AccountConstant.TEN_IM_ADMIN_IDENTIFIER + "&sdkappid=" + AccountConstant.TEN_IM_APP_ID + "&contenttype=json";
    public static final String TEN_SEND_MSG = "v4/group_open_http_svc/send_group_msg?usersig=" + AccountConstant.TEN_IM_ADMIN_SIG + "&apn=1&identifier=" + AccountConstant.TEN_IM_ADMIN_IDENTIFIER + "&sdkappid=" + AccountConstant.TEN_IM_APP_ID + "&contenttype=json";
    public static final String TEN_GET_GROUP_MEMBER = "v4/group_open_http_svc/get_group_member_info?usersig=" + AccountConstant.TEN_IM_ADMIN_SIG + "&identifier=" + AccountConstant.TEN_IM_ADMIN_IDENTIFIER + "&sdkappid=" + AccountConstant.TEN_IM_APP_ID + "&contenttype=json";
    public static final String TEN_GET_MEMBERS_STATUS = "v4/openim/querystate?usersig=" + AccountConstant.TEN_IM_ADMIN_SIG + "&identifier=" + AccountConstant.TEN_IM_ADMIN_IDENTIFIER + "&sdkappid=" + AccountConstant.TEN_IM_APP_ID + "&contenttype=json";
    public static final String TEN_GET_GROUP_JOIN = "v4/group_open_http_svc/get_joined_group_list?usersig=" + AccountConstant.TEN_IM_ADMIN_SIG + "&identifier=" + AccountConstant.TEN_IM_ADMIN_IDENTIFIER + "&sdkappid=" + AccountConstant.TEN_IM_APP_ID + "&contenttype=json";
    public static final String TEN_DEL_GROUP_MEMBER = "v4/group_open_http_svc/delete_group_member?usersig=" + AccountConstant.TEN_IM_ADMIN_SIG + "&identifier=" + AccountConstant.TEN_IM_ADMIN_IDENTIFIER + "&sdkappid=" + AccountConstant.TEN_IM_APP_ID + "&contenttype=json";

    public static final String TEN_SEND_MSG_SYS = "v4/group_open_http_svc/send_group_system_notification";

}
