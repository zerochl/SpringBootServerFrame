package cn.tzmedia.barrageserver.message.entity;

import cn.tzmedia.barrageserver.common.entity.servermsg.RequestSilkBagUseMsg;
import cn.tzmedia.barrageserver.common.utils.UrlUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by zero大神 on 2017/12/14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseSilkBagUseMsg extends BaseResponseMsg{
    private String silkBagId;
    private String silkBagImage;
    private String silkBagName;
    private int silkBagType;
    private String bigSilkBagImage;
    private String suffix;
    private String content;
    private int timeout;
    private Date startTime;
    private String pkType;
    public ResponseSilkBagUseMsg(){}
    public ResponseSilkBagUseMsg(RequestSilkBagUseMsg requestSilkBagUseMsg){
        this.activityId = requestSilkBagUseMsg.getActivityId();
        this.content = requestSilkBagUseMsg.getContent();
        this.silkBagId = requestSilkBagUseMsg.getSilkBagId();
        this.silkBagImage = UrlUtils.getRealUrl(requestSilkBagUseMsg.getSilkBagImage());
        this.silkBagName = requestSilkBagUseMsg.getSilkBagName();
        this.silkBagType = requestSilkBagUseMsg.getSilkBagType();
        this.bigSilkBagImage = UrlUtils.getRealUrl(requestSilkBagUseMsg.getBigSilkBagImage());
        this.suffix = requestSilkBagUseMsg.getSuffix();
        this.pkType = requestSilkBagUseMsg.getPkType();
        this.teamCode = requestSilkBagUseMsg.getTeamCode();
        this.teamName = requestSilkBagUseMsg.getTeamName();
        this.timeout = requestSilkBagUseMsg.getTimeout();
        setType(requestSilkBagUseMsg.getMsgType());

        setUser(requestSilkBagUseMsg);
    }
}
