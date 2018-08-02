package com.zero.barrageserver.common.entity.servermsg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by zero大神 on 2017/12/14.
 */
@Data
public class RequestHotPointEntity {
    @JSONField(name = "_id")
    private String id;
    @JSONField(name = "activityid")
    private String activityId;
    private int points;
    private String usertoken;
    private int level;
    private String image;
    @JSONField(name = "nickname")
    private String nickName;
    private int levelRange;
    private String sign;
    @JSONField(name = "userrole")
    private String userRole;
    @JSONField(name = "userid")
    private int userId;
    @JSONField(name = "artistid")
    private String artistId;
    private int cannice;
    @JSONField(name = "nicecount")
    private int niceCount;
    @JSONField(name = "mylike")
    private int myLike; // 我关注的数量
    private int sex;
}
