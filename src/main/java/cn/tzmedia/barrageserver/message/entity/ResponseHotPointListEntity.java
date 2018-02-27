package cn.tzmedia.barrageserver.message.entity;

import cn.tzmedia.barrageserver.common.constant.Constant;
import cn.tzmedia.barrageserver.common.entity.servermsg.RequestHotPointEntity;
import cn.tzmedia.barrageserver.common.entity.servermsg.RequestHotPointListEntity;
import cn.tzmedia.barrageserver.common.entity.servermsg.RequestNormalHotMsg;
import cn.tzmedia.barrageserver.dbserver.model.HotCreatorTable;
import cn.tzmedia.barrageserver.dbserver.model.HotTotalTable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/15.
 */
@Data
public class ResponseHotPointListEntity {
    private long total;
    private List<ResponseHotPointEntity> hotCreatorList;

    public ResponseHotPointListEntity(){}

    public ResponseHotPointListEntity(HotTotalTable hotTotalTable,List<HotCreatorTable> hotCreatorTableList){
        this.total = hotTotalTable.getTotal();
        if(null == hotCreatorTableList){
            return;
        }
        this.hotCreatorList = new ArrayList<>();
        for(HotCreatorTable hotCreatorTable:hotCreatorTableList){
            //如果属于普通节，teamCode都为0
            if(hotCreatorTable.getTeamCode() == hotTotalTable.getTeamCode()){
                hotCreatorList.add(new ResponseHotPointEntity(hotCreatorTable));
                if(hotCreatorList.size() >= Constant.HOT_POINTS_USER_MAX_COUNT){
                    break;
                }
            }
        }
    }

    public ResponseHotPointListEntity(RequestHotPointListEntity requestHotPointListEntity){
        this.total = requestHotPointListEntity.getTotal();
        this.hotCreatorList = new ArrayList<>();
        if(null != requestHotPointListEntity.getPoints()){
            for(RequestHotPointEntity requestHotPoint:requestHotPointListEntity.getPoints()){
                ResponseHotPointEntity responseHotPoint = new ResponseHotPointEntity(requestHotPoint);
                hotCreatorList.add(responseHotPoint);
                if(hotCreatorList.size() >= Constant.HOT_POINTS_USER_MAX_COUNT){
                    break;
                }
            }
        }
    }

    public ResponseHotPointListEntity(RequestNormalHotMsg requestNormalHotMsg){
        this.total = requestNormalHotMsg.getTotal();
        this.hotCreatorList = new ArrayList<>();
        if(null != requestNormalHotMsg.getPoints()){
            for(RequestHotPointEntity requestHotPoint:requestNormalHotMsg.getPoints()){
                ResponseHotPointEntity responseHotPoint = new ResponseHotPointEntity(requestHotPoint);
                hotCreatorList.add(responseHotPoint);
                if(hotCreatorList.size() >= Constant.HOT_POINTS_USER_MAX_COUNT){
                    break;
                }
            }
        }
    }

}
