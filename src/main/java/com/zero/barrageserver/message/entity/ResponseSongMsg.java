package com.zero.barrageserver.message.entity;

import com.zero.barrageserver.common.entity.servermsg.RequestSongMsg;
import com.zero.barrageserver.common.utils.UrlUtils;
import com.zero.barrageserver.common.entity.servermsg.RequestSongMsg;
import com.zero.barrageserver.common.utils.UrlUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Administrator on 2017/12/12.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseSongMsg extends BaseResponseMsg{
    private String song;
    private String comment;
    private String songImage;
    private String artistId;
    private String artistName;
    public ResponseSongMsg(){}
    public ResponseSongMsg(RequestSongMsg requestSongMsg){
        this.activityId = requestSongMsg.getActivityId();
        this.artistId = requestSongMsg.getArtistId();
        this.artistName = requestSongMsg.getArtistName();
        this.comment = requestSongMsg.getContent();
        this.song = requestSongMsg.getSongName();
        this.songImage = UrlUtils.getRealUrl(requestSongMsg.getSongImage());
        this.setType(requestSongMsg.getMsgType());

        setUser(requestSongMsg);
    }
}
