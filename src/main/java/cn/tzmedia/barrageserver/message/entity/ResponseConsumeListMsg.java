package cn.tzmedia.barrageserver.message.entity;

import cn.tzmedia.barrageserver.common.entity.servermsg.RequestConsumeEntity;
import cn.tzmedia.barrageserver.common.entity.servermsg.RequestConsumeListMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2018/1/22.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseConsumeListMsg extends BaseResponseMsg{
    private int total;
    private List<ResponseConsumeEntity> consumeList;
    public ResponseConsumeListMsg(RequestConsumeListMsg requestConsumeListMsg){
        this.activityId = requestConsumeListMsg.getActivityId();
        this.total = requestConsumeListMsg.getTotal();
        this.setType(requestConsumeListMsg.getMsgType());
        if(null != requestConsumeListMsg.getConsumeList()){
            consumeList = new ArrayList<>();
            for(RequestConsumeEntity requestConsumeEntity:requestConsumeListMsg.getConsumeList()){
                ResponseConsumeEntity responseConsumeEntity = new ResponseConsumeEntity(requestConsumeEntity);
                consumeList.add(responseConsumeEntity);
            }
        }
        setUser(requestConsumeListMsg);
    }
}
