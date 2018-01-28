package cn.tzmedia.barrageserver.common.entity.servermsg;

import lombok.Data;

/**
 * Created by zero大神 on 2018/1/22.
 */
@Data
public class RequestImageWallMsg extends BaseRequestMsgEntity{
    private int time;
    private String toUserName;
    private String toUserImage;
    private String userGifBg;
    private String toUserGifBg;
    private String imageForWall;
    private String themeGifUrl;
}
