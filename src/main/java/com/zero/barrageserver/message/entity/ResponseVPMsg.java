package com.zero.barrageserver.message.entity;

import com.zero.barrageserver.common.entity.servermsg.RequestVPMsg;
import com.zero.barrageserver.common.utils.UrlUtils;
import com.alibaba.fastjson.annotation.JSONField;
import com.zero.barrageserver.common.entity.servermsg.RequestVPMsg;
import com.zero.barrageserver.common.utils.UrlUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zero大神 on 2017/12/14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseVPMsg extends BaseResponseMsg{
    private String vpId;
    private String vpImage;
    private String gifImage;//gif动画
    private String comment;
    private int gifResId;
    private int activityType;
    @JSONField(name = "isFullAnimation")
    private boolean isFullAnimation;
    private int gifCount;
    private String gifType;
    private String gifFileRes;
    private String vpName;
    private String pkType;

    @JSONField(name = "isComboAnimation")
    private boolean isComboAnimation;
    private int comboBarrageCount;
    private int comboVPCount;
    private int comboMeetCount;

    public ResponseVPMsg(){}
    public ResponseVPMsg(RequestVPMsg requestVPMsg){
        this.activityId = requestVPMsg.getActivityId();
        this.count = requestVPMsg.getCount();
        this.gifCount = requestVPMsg.getGifCount();
        this.gifImage = requestVPMsg.getGifImage();
        this.gifType = requestVPMsg.getGifType();
        this.isFullAnimation = requestVPMsg.isFullAnimation();
        this.teamCode = requestVPMsg.getTeamCode();
        this.teamName = requestVPMsg.getTeamName();
        this.setType(requestVPMsg.getMsgType());
        this.vpId = requestVPMsg.getVpId();
        this.vpImage = UrlUtils.getRealUrl(requestVPMsg.getVpImage());
        this.vpName = requestVPMsg.getVpName();

        this.isComboAnimation = requestVPMsg.isComboAnimation();
        this.comboBarrageCount = requestVPMsg.getComboBarrageCount();
        this.comboVPCount = requestVPMsg.getComboVPCount();
        this.comboMeetCount = requestVPMsg.getComboMeetCount();

        setUser(requestVPMsg);
    }
}
