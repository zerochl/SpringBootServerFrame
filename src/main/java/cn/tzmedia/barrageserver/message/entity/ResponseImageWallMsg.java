package cn.tzmedia.barrageserver.message.entity;

import cn.tzmedia.barrageserver.common.entity.servermsg.RequestImageWallMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zero大神 on 2018/1/22.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseImageWallMsg extends BaseResponseMsg{
    private String comment;
    private int time;
    private String toUserName;
    private String toUserImage;
    private String imageForWall;
    private String userGifBg;
    private String toUserGifBg;
    private String themeGifUrl;
    public ResponseImageWallMsg(RequestImageWallMsg requestImageWallMsg){
        this.activityId = requestImageWallMsg.getActivityId();
        this.comment = requestImageWallMsg.getContent();
        this.time = requestImageWallMsg.getTime();
        this.toUserName = requestImageWallMsg.getToUserName();
        this.toUserImage = requestImageWallMsg.getToUserImage();
        this.userGifBg = requestImageWallMsg.getUserGifBg();
        this.toUserGifBg = requestImageWallMsg.getToUserGifBg();
        this.imageForWall = requestImageWallMsg.getImageForWall();
        this.themeGifUrl = requestImageWallMsg.getThemeGifUrl();
        this.setType(requestImageWallMsg.getMsgType());

        setUser(requestImageWallMsg);
    }
}
