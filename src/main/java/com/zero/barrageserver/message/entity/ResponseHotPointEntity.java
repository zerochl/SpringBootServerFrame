package com.zero.barrageserver.message.entity;

import com.zero.barrageserver.common.entity.servermsg.RequestHotPointEntity;
import com.zero.barrageserver.common.utils.UrlUtils;
import com.zero.barrageserver.dbserver.model.HotCreatorTable;
import com.zero.barrageserver.common.entity.servermsg.RequestHotPointEntity;
import com.zero.barrageserver.common.utils.UrlUtils;
import com.zero.barrageserver.dbserver.model.HotCreatorTable;
import lombok.Data;

/**
 * Created by zero大神 on 2017/12/15.
 */
@Data
public class ResponseHotPointEntity {
    private int points;
    private String userRole;
    private String nickName;
    private String sign;
    private int sex;
    private int nickCount;
    private String image;
    private String usertoken;
    private int levelRange;
    private int level;
    private int userId;
    private String activityId;
    private String id;
    private int cannice;
    private int myLike;
    private String artistId;

    public ResponseHotPointEntity(){}

    public ResponseHotPointEntity(HotCreatorTable hotCreatorTable){
        this.userRole = hotCreatorTable.getUser().getUserRole();
        this.nickName = hotCreatorTable.getUser().getNickName();
        this.image = UrlUtils.getRealUrl(hotCreatorTable.getUser().getImageList() != null && hotCreatorTable.getUser().getImageList().size() > 0
                ? hotCreatorTable.getUser().getImageList().get(0) : "");
        this.levelRange = hotCreatorTable.getUser().getLevelRange();
        this.level = hotCreatorTable.getUser().getLevel();
        this.activityId = hotCreatorTable.getActivityId();
        this.id = hotCreatorTable.getId();
    }

    public ResponseHotPointEntity(RequestHotPointEntity requestHotPointEntity){
        this.points = requestHotPointEntity.getPoints();
        this.userRole = requestHotPointEntity.getUserRole();
        this.nickName = requestHotPointEntity.getNickName();
        this.sign = requestHotPointEntity.getSign();
        this.sex = requestHotPointEntity.getSex();
        this.nickCount = requestHotPointEntity.getNiceCount();
        this.image = UrlUtils.getRealUrl(requestHotPointEntity.getImage());
        this.usertoken = requestHotPointEntity.getUsertoken();
        this.levelRange = requestHotPointEntity.getLevelRange();
        this.level = requestHotPointEntity.getLevel();
        this.userId = requestHotPointEntity.getUserId();
        this.activityId = requestHotPointEntity.getActivityId();
        this.id = requestHotPointEntity.getId();
        this.cannice = requestHotPointEntity.getCannice();
        this.myLike = requestHotPointEntity.getMyLike();
        this.artistId = requestHotPointEntity.getArtistId();
    }
}
