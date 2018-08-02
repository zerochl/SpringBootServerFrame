package com.zero.barrageserver.common.entity.servermsg;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zero大神 on 2017/12/14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RequestCommentMsg extends BaseRequestMsgEntity{
    private String ninePatchThemeImage;
    private String fontColor;
//    private List<AtUsersInfo> atUsers;
    private String themeImage;
    private int topNum;
    private int leftNum;
    private int bottomNum;
    private int rightNum;
    private String usernameColor;
}
