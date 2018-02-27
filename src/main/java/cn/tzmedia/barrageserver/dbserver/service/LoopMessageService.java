package cn.tzmedia.barrageserver.dbserver.service;

import cn.tzmedia.barrageserver.common.constant.Constant;
import cn.tzmedia.barrageserver.common.convert.ClientMsgConvert;
import cn.tzmedia.barrageserver.message.entity.BaseResponseMsg;
import cn.tzmedia.barrageserver.message.entity.BaseResponseRootMsg;
import cn.tzmedia.barrageserver.message.entity.ResponseVPMsg;
import cn.tzmedia.barrageserver.dbserver.model.ActivityTable;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Created by zero大神 on 2017/12/21.
 */
@Service
@AllArgsConstructor
@Log4j2
public class LoopMessageService {
    private ClientMsgConvert clientMsgConvert;
    private ActivityService activityService;
    private BarrageConfigService shopSetService;

    public BaseResponseRootMsg getLoopMsg(BaseResponseRootMsg rootMsg){
        if(null == rootMsg){
            return null;
        }
        ActivityTable nowActivity = activityService.getNowActivity(rootMsg.getShopId());
        if(null == nowActivity){
            //当前非演出节
            return null;
        }
        if(!rootMsg.getActivityId().equals(nowActivity.getId())){
            //不是同一节也不需要循环了
            return null;
        }
        if(rootMsg.isLoop()){
            //是循环处理过的消息，直接发送
            rootMsg.setLoopIntervalTime(shopSetService.getBarrageLoopIntervalTime(rootMsg.getShopId()));
            log.info("loop time:" + shopSetService.getBarrageLoopIntervalTime(rootMsg.getShopId()));
            return rootMsg;
        }
        BaseResponseMsg responseMsg = clientMsgConvert.convertToClientMsg(rootMsg);
        switch (responseMsg.getType()){
            case Constant.BARRAGE_TYPE_VP:
                responseMsg = getVPLoopMsg((ResponseVPMsg)responseMsg);
                break;
        }
        if(null != responseMsg){
            rootMsg.setLoop(true);
            rootMsg.setLoopIntervalTime(shopSetService.getBarrageLoopIntervalTime(rootMsg.getShopId()));
            log.info("loop time:" + shopSetService.getBarrageLoopIntervalTime(rootMsg.getShopId()));
            rootMsg.setValue(JSONObject.toJSONString(responseMsg));
            return rootMsg;
        }else{
            return null;
        }
    }

    private ResponseVPMsg getVPLoopMsg(ResponseVPMsg vpMsg){
        if(null == vpMsg){
            return null;
        }
        if(!vpMsg.isFullAnimation()){
            return null;
        }
        return vpMsg;
    }
}
