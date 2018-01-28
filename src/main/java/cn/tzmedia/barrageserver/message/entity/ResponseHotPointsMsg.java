package cn.tzmedia.barrageserver.message.entity;

import cn.tzmedia.barrageserver.common.constant.Constant;
import cn.tzmedia.barrageserver.common.entity.servermsg.RequestHotPointListEntity;
import cn.tzmedia.barrageserver.common.entity.servermsg.RequestNormalHotMsg;
import cn.tzmedia.barrageserver.common.entity.servermsg.RequestPKHotMsg;
import cn.tzmedia.barrageserver.server.model.HotCreatorTable;
import cn.tzmedia.barrageserver.server.model.HotTotalTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/15.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseHotPointsMsg extends BaseResponseMsg{
    private List<ResponseHotPointListEntity> hotItemList;
    public ResponseHotPointsMsg(){}
    public ResponseHotPointsMsg(List<HotTotalTable> hotTotalTableList,List<HotCreatorTable> hotCreatorTableList){
        if(null == hotTotalTableList){
            return;
        }
        this.setType(Constant.BARRAGE_TYPE_HOTPOINTS);
        this.hotItemList = new ArrayList<>();
        for(HotTotalTable hotTotalTable:hotTotalTableList){
            ResponseHotPointListEntity msgItemHotItem = new ResponseHotPointListEntity(hotTotalTable,hotCreatorTableList);
            hotItemList.add(msgItemHotItem);
        }
    }
    public ResponseHotPointsMsg(RequestPKHotMsg requestPKHotMsg){
        this.activityId = requestPKHotMsg.getActivityId();
        hotItemList = new ArrayList<>();
        this.setType(Constant.BARRAGE_TYPE_HOTPOINTS);
        for(RequestHotPointListEntity requestHotPointList:requestPKHotMsg.getHotPoints()){
            ResponseHotPointListEntity responseHotPointList = new ResponseHotPointListEntity(requestHotPointList);
            hotItemList.add(responseHotPointList);
            //之所以有如下操作，因为NodeServer过来的热度多了一个总热度，弹幕不需要，为了减小字符串，需要排除
            if(requestPKHotMsg.getHotPoints().size() - hotItemList.size() <= 1){
                break;
            }
        }
    }

    public ResponseHotPointsMsg(RequestNormalHotMsg requestNormalHotMsg){
        this.activityId = requestNormalHotMsg.getActivityId();
        hotItemList = new ArrayList<>();
        this.setType(Constant.BARRAGE_TYPE_HOTPOINTS);
        ResponseHotPointListEntity responseHotPointList = new ResponseHotPointListEntity(requestNormalHotMsg);
        hotItemList.add(responseHotPointList);
    }

}
