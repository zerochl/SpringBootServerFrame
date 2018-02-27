package cn.tzmedia.barrageserver.dbserver.service;

import cn.tzmedia.barrageserver.message.entity.MsgItemHot;
import cn.tzmedia.barrageserver.message.entity.ResponseHotPointsMsg;
import cn.tzmedia.barrageserver.dbserver.dao.HotCreatorDao;
import cn.tzmedia.barrageserver.dbserver.dao.HotTotalDao;
import cn.tzmedia.barrageserver.dbserver.model.HotCreatorTable;
import cn.tzmedia.barrageserver.dbserver.model.HotTotalTable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/18.
 */
@Service
@AllArgsConstructor
public class HotService {
    private HotCreatorDao hotCreatorDao;
    private HotTotalDao hotTotalDao;
    public MsgItemHot getHotListForShow(String activityId){
        List<HotTotalTable> hotTotalTableList = hotTotalDao.getHotsByActivityId(activityId);
        List<HotCreatorTable> hotCreatorTableList = hotCreatorDao.getTopCreator(activityId);
        return new MsgItemHot(hotTotalTableList,hotCreatorTableList);
    }

    public ResponseHotPointsMsg getHotMessage(String activityId){
        List<HotTotalTable> hotTotalTableList = hotTotalDao.getHotsByActivityId(activityId);
        List<HotCreatorTable> hotCreatorTableList = hotCreatorDao.getTopCreator(activityId);
        return new ResponseHotPointsMsg(hotTotalTableList,hotCreatorTableList);
    }

    public ResponseHotPointsMsg getHotTotalMessage(String activityId){
        List<HotTotalTable> hotTotalTableList = hotTotalDao.getHotsByActivityId(activityId);
        return new ResponseHotPointsMsg(hotTotalTableList,null);
    }
}
