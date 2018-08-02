package com.zero.barrageserver.common.entity.servermsg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by zero大神 on 2017/12/12.
 */
@Data
public class BaseRequestMsgEntity {
    @JSONField(name = "_id")
    private String id;
    @JSONField(name="msg_id")
    public String msgId;
    public String msgType;
    public String content;
    @JSONField(name="userlevel")
    public int userLevel;
    public String usertoken;
    @JSONField(name="username")
    public String userName;
    @JSONField(name="userimage")
    public String userImage;
    @JSONField(name="userrole")
    public String userRole;
    public int levelRange;
    public int count;//记录本次消息是第几次，累计次数
    public boolean isLocalMessage;//标志是否是本地发送的
    public boolean isDelayMessage;//标志位delay的消息
    @JSONField(name="activityid")
    public String activityId;//群ID
    public String commentId;
    public int position;//聊天记录在聊天区域的position
    public boolean shouldShowInclude = false;
    private double price;
    private double totalAmount;
}
