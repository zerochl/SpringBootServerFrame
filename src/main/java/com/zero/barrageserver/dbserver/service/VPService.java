package com.zero.barrageserver.dbserver.service;

import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.message.entity.BaseResponseRootMsg;
import com.zero.barrageserver.message.entity.ResponseMultiHitMsg;
import com.zero.barrageserver.message.entity.ResponseVPMsg;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/19.
 */
@Service
@Log4j2
public class VPService {

    public List<BaseResponseRootMsg> getMultiHitMsgList(BaseResponseRootMsg rootMsg){
        if(null == rootMsg || rootMsg.isLoop()){
            //循环消息是不会产生连击的
            return null;
        }
        ResponseVPMsg vpMsg = JSONObject.parseObject(rootMsg.getValue(), ResponseVPMsg.class);
        log.info("vp combo meet count" + vpMsg.getComboMeetCount());
        if(!vpMsg.isComboAnimation() || vpMsg.getComboMeetCount() == 0){
            return null;
        }
        ArrayList<BaseResponseRootMsg> multiHitRootMsgList = new ArrayList<>();
        if(vpMsg.getCount() % vpMsg.getComboMeetCount() == 0){
            //符合条件，创造连击消息
            for(int i = 0;i < vpMsg.getComboBarrageCount();i++){
                ResponseMultiHitMsg multiHitMsg = new ResponseMultiHitMsg(vpMsg);
                BaseResponseRootMsg multiHitRootMsg = new BaseResponseRootMsg(rootMsg.getShopId(),
                        rootMsg.getActivityId(),0,Constant.BARRAGE_TYPE_MULTI_HIT,JSONObject.toJSONString(multiHitMsg),false);
                multiHitRootMsgList.add(multiHitRootMsg);
            }
        }
        return multiHitRootMsgList;
    }
}
