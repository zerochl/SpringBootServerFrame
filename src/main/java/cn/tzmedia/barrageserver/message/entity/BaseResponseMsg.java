package cn.tzmedia.barrageserver.message.entity;

import cn.tzmedia.barrageserver.common.entity.servermsg.BaseRequestMsgEntity;
import cn.tzmedia.barrageserver.common.utils.UrlUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zero大神 on 2017/12/14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseResponseMsg extends BaseResponseRootMsg{
    private String id;
    public String portraitImage;
    public String nickName;
    public int userLevel;
    public int levelRange;
    public String userRole;
    public String activityId;
    public String usertoken;
    public int count;
//    public boolean isFree = true;
    public int teamCode;
    public String teamName;
    public BaseResponseMsg(){}
    public BaseResponseMsg(String shopId, String activityId, int weight, String type, String value,boolean isProgram){
        super(shopId,activityId,weight,type,value,isProgram);
    }

    public void setUser(BaseRequestMsgEntity requestMsgEntity){
        this.id = requestMsgEntity.getId();

        this.levelRange = requestMsgEntity.getLevelRange();
        this.userLevel = requestMsgEntity.getUserLevel();
        this.userRole = requestMsgEntity.getUserRole();
        this.usertoken = requestMsgEntity.getUsertoken();
        this.nickName = requestMsgEntity.getUserName();
        this.portraitImage = UrlUtils.getRealUrl(requestMsgEntity.getUserImage());
    }

    public void setUser(BaseResponseMsg responseMsgEntity){
        this.id = responseMsgEntity.getId();

        this.levelRange = responseMsgEntity.getLevelRange();
        this.userLevel = responseMsgEntity.getUserLevel();
        this.userRole = responseMsgEntity.getUserRole();
        this.usertoken = responseMsgEntity.getUsertoken();
        this.nickName = responseMsgEntity.getNickName();
        this.portraitImage = UrlUtils.getRealUrl(responseMsgEntity.getPortraitImage());
    }

}
