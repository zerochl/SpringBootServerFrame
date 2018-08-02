package com.zero.barrageserver.message;

import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.common.iface.IQueueObserver;
import com.zero.barrageserver.common.manager.ExecutorManager;
import com.zero.barrageserver.common.manager.MsgConfigManager;
import com.zero.barrageserver.common.manager.TimerManager;
import com.zero.barrageserver.common.utils.StringUtil;
import com.zero.barrageserver.message.entity.BaseResponseMsg;
import com.zero.barrageserver.message.entity.BaseResponseRootMsg;
import com.alibaba.fastjson.JSONObject;
import com.zero.barrageserver.common.iface.IQueueObserver;
import com.zero.barrageserver.common.utils.StringUtil;
import com.zero.barrageserver.message.entity.BaseResponseMsg;
import com.zero.barrageserver.message.entity.BaseResponseRootMsg;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Vector;

/**
 * Created by zero大神 on 2017/12/12.
 */
@Component
public class MessageProducer {
    @Autowired
    private TimerManager timerManager;
    @Autowired
    private MsgConfigManager msgConfigManager;
    private Vector<IQueueObserver<BaseResponseRootMsg>> msgObserverList = new Vector<>();
    public void registerObserver(IQueueObserver<BaseResponseRootMsg> msgObserver){
        msgObserverList.add(msgObserver);
    }

    public void unregisterObserver(IQueueObserver<BaseResponseRootMsg> msgObserver){
        msgObserverList.remove(msgObserver);
    }

    public void receiveMessage(BaseResponseRootMsg msg){
        //lamda格式，请注意
        Observable.create(observableEmitter -> dispatchMessage(msg))
                //单线程池接收，保证有序消息可以有序插入队列（消息队列插入并不耗时）
                .subscribeOn(Schedulers.from(ExecutorManager.getSingleExecutorByKey(Constant.SINGLE_THREAD_EXECUTOR_TYPE_MESSAGE)))
//                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void receiveMessage(BaseResponseMsg msg, String shopId, boolean isProgram, int delay){
        if(null == msg || StringUtil.isEmpty(shopId)){
            return;
        }
        timerManager.postFaster(new Runnable() {
            @Override
            public void run() {
                receiveMessage(msg,shopId,isProgram);
            }
        },delay);
    }

    public void receiveMessage(List<BaseResponseMsg> responseMsgList,String shopId,boolean isProgram,int delay){
        if(null == responseMsgList || responseMsgList.size() == 0 || StringUtil.isEmpty(shopId)){
            return;
        }
        timerManager.postFaster(new Runnable() {
            @Override
            public void run() {
                for(BaseResponseMsg responseMsg:responseMsgList){
                    receiveMessage(responseMsg,shopId,isProgram);
                }
            }
        },delay);
    }
    public void receiveMessage(BaseResponseMsg responseMsg,String shopId,boolean isProgram){
        BaseResponseRootMsg rootMsg = new BaseResponseRootMsg(shopId,responseMsg.getActivityId(),0,
                responseMsg.getType(), JSONObject.toJSONString(responseMsg),isProgram);
        receiveMessage(rootMsg);
    }

    private void dispatchMessage(BaseResponseRootMsg msg){
        //此处可以做一些所有消息都会涉及到的逻辑，例如添加消息的循环时间等等，此逻辑NodeJs并不会带过来，可以在这个入口统一处理
        msgConfigManager.equipMsgConfig(msg);
        for(IQueueObserver<BaseResponseRootMsg> msgObserver:msgObserverList){
            msgObserver.receiveMessage(msg.getType(),msg);
        }
    }

}
