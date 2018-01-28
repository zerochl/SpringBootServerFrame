package cn.tzmedia.barrageserver.message.entity;

import cn.tzmedia.barrageserver.common.entity.servermsg.RequestConsumeEntity;
import lombok.Data;

/**
 * Created by zero大神 on 2018/1/22.
 */
@Data
public class ResponseConsumeEntity {
    private double consumeAmount;
    private String userRole;
    private String usertoken;
    private String userImage;
    private String nickName;
    private String userHeadBg;
    private String userHeadGifBg;
    private int levelRange;
    private int level;
    public ResponseConsumeEntity(RequestConsumeEntity requestConsumeEntity){
        this.consumeAmount = requestConsumeEntity.getConsumeAmount();
        this.userRole = requestConsumeEntity.getUserRole();
        this.usertoken = requestConsumeEntity.getUsertoken();
        this.userImage = requestConsumeEntity.getUserImage();
        this.nickName = requestConsumeEntity.getUserName();
        this.levelRange = requestConsumeEntity.getLevelRange();
        this.level = requestConsumeEntity.getLevel();
        this.userHeadBg = requestConsumeEntity.getUserHeadBg();
        this.userHeadGifBg = requestConsumeEntity.getUserHeadGifBg();
    }
}
