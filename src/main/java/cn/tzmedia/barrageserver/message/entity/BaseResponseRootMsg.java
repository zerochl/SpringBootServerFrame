package cn.tzmedia.barrageserver.message.entity;

import cn.tzmedia.barrageserver.common.entity.BaseQueueEntity;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Administrator on 2017/12/12.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseResponseRootMsg extends BaseQueueEntity implements Cloneable{
    private String type;
    @JSONField(name = "isLoop")
    private boolean isLoop;
    //这是一个神奇的字段
    @JSONField(name = "isProgram")
    private boolean isProgram;
    private int scrollTime;
    private int loopIntervalTime;
    private String value;
    private String shopId;
    private String activityId;
    public BaseResponseRootMsg(){}
    public BaseResponseRootMsg(BaseResponseRootMsg rootMsg,String value){
        this.weight = rootMsg.getWeight();
        this.type = rootMsg.getType();
        this.shopId = rootMsg.getShopId();
        this.activityId = rootMsg.getActivityId();
        this.value = value;
    }
    public BaseResponseRootMsg(String shopId, String activityId, int weight, String type, String value,boolean isProgram){
        this.weight = weight;
        this.type = type;
        this.value = value;
        this.shopId = shopId;
        this.activityId = activityId;
        this.isProgram = isProgram;
    }

    public String toNormalMsgString(){
        return "type:" + type + ";value:" + value + ";shopId:" + shopId + ";activityId:" + activityId;
    }

//    public BaseResponseRootMsg getClone(){
//        BaseResponseRootMsg cloneMsg = new BaseResponseRootMsg(this,this.getValue());
//        cloneMsg.setLoop(this.isLoop());
//        cloneMsg.setLoopIntervalTime(this.getLoopIntervalTime());
//        return cloneMsg;
//    }

}
