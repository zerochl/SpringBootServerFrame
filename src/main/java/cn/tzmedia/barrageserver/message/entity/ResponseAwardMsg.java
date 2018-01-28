package cn.tzmedia.barrageserver.message.entity;

import cn.tzmedia.barrageserver.common.entity.servermsg.RequestAwardMsg;
import cn.tzmedia.barrageserver.common.utils.UrlUtils;
import cn.tzmedia.barrageserver.server.model.AnimationTable;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zero大神 on 2017/12/14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseAwardMsg extends BaseResponseMsg{
    private String comment;
    private String artistId;
    private String artistName;
    private String awardImage;
    @JSONField(name = "isFullAnimation")
    private boolean isFullAnimation;
    private int gifCount;
    private String gifType;
    private String gifFileRes;
    private String vpName;
    private String pkType;
    private double price;
    public ResponseAwardMsg(){}
    public ResponseAwardMsg(RequestAwardMsg requestAwardMsg){
        this.activityId = requestAwardMsg.getActivityId();
        this.comment = requestAwardMsg.getContent();
        this.artistId = requestAwardMsg.getArtistId();
        this.artistName = requestAwardMsg.getArtistName();
        this.awardImage = UrlUtils.getRealUrl(requestAwardMsg.getAwardImage());
        this.count = requestAwardMsg.getCount();
        this.teamCode = requestAwardMsg.getTeamCode();
        this.teamName = requestAwardMsg.getTeamName();
        this.price = requestAwardMsg.getPrice();
        this.gifCount = requestAwardMsg.getGifCount();
        this.gifType = requestAwardMsg.getGifType();
        this.vpName = requestAwardMsg.getVpName();
        this.isFullAnimation = requestAwardMsg.isFullAnimation();
        this.setType(requestAwardMsg.getMsgType());

        setUser(requestAwardMsg);
    }

}
