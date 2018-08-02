package com.zero.barrageserver.dbserver.service;

import com.zero.barrageserver.common.constant.ErrorConstant;
import com.zero.barrageserver.common.convert.ClientMsgConvert;
import com.zero.barrageserver.common.entity.servermsg.BaseRequestMsgEntity;
import com.zero.barrageserver.common.entity.servermsg.BaseRequestRootMsgEntity;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.convert.ServerMsgConvert;
import com.zero.barrageserver.common.utils.StringUtil;
import com.zero.barrageserver.message.MessageProducer;
import com.zero.barrageserver.message.entity.BaseResponseMsg;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.servermsg.BaseRequestMsgEntity;
import com.zero.barrageserver.common.entity.servermsg.BaseRequestRootMsgEntity;
import com.zero.barrageserver.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/13.
 */
@Service
public class NormalMsgService {
    @Autowired
    private ServerMsgConvert serverMsgConvert;
    @Autowired
    private ClientMsgConvert clientMsgConvert;
    @Autowired
    private MessageProducer messageProducer;
    @Autowired
    private ActivityService activityService;

    public BaseResponseEntity receiveRequest(BaseRequestRootMsgEntity msgEntity){
        if(null == msgEntity || StringUtil.isEmpty(msgEntity.getValue()) || StringUtil.isEmpty(msgEntity.getMsgtype())){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
//        //解析服务端发送来的消息
//        BaseRequestMsgEntity requestMsgEntity = serverMsgConvert.convertToServerMsg(msgEntity);
//        //转化成客户端需要的消息
//        List<BaseResponseMsg> responseMsgList = clientMsgConvert.convertToClientMsg(requestMsgEntity);

        List<BaseResponseMsg> responseMsgList = convertFromServerToClient(msgEntity);
        if(null == responseMsgList || responseMsgList.size() == 0){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_MSG_UNWANTED);
        }
        //shopId决定了发送到哪个群组
        String shopId = activityService.getShopIdByActivityId(msgEntity.getActivityid());
        messageProducer.receiveMessage(responseMsgList,shopId,false,0);
        return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OK);
    }

    /**
     * 从服务端过来的消息，直接Convert到弹幕客户端需要的消息
     * @param msgEntity
     * @return
     */
    public List<BaseResponseMsg> convertFromServerToClient(BaseRequestRootMsgEntity msgEntity){
        if(null == msgEntity || StringUtil.isEmpty(msgEntity.getValue()) || StringUtil.isEmpty(msgEntity.getMsgtype())){
            return null;
        }
        //解析服务端发送来的消息,此消息其实为手机需要的消息
        BaseRequestMsgEntity requestMsgEntity = serverMsgConvert.convertToServerMsg(msgEntity);
        //转化成客户端需要的消息
        List<BaseResponseMsg> responseMsgList = clientMsgConvert.convertToClientMsg(requestMsgEntity);
        if(null == responseMsgList || responseMsgList.size() == 0){
            return null;
        }
        return responseMsgList;
    }
}
