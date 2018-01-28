package cn.tzmedia.barrageserver.common.entity.servermsg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zero大神 on 2017/12/14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RequestVPMsg extends BaseRequestMsgEntity{
    @JSONField(name = "vpimage")
    private String vpImage;
    @JSONField(name = "vpname")
    private String vpName;
    @JSONField(name = "vpid")
    private String vpId;
    private int teamCode;
    private int contentType;//区分是否是pk类型,1:pk,0:非pk
    private String teamName;
    private int hotPoints;
    private int giftType;
    @JSONField(name = "pktype")
    private String pkType;
    private int gifCount;
    private String gifImage;
    private String gifType;
    @JSONField(name = "isComboAnimation")
    private boolean isComboAnimation;
    @JSONField(name = "meetcount")
    private int comboMeetCount;
    @JSONField(name = "comboCount")
    private int comboBarrageCount;
    @JSONField(name = "vpimagecount")
    private int comboVPCount;
    @JSONField(name = "isFullAnimation")
    private boolean isFullAnimation;
}
