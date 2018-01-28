package cn.tzmedia.barrageserver.message.base;

import cn.tzmedia.barrageserver.activeapi.service.TenActiveService;
import cn.tzmedia.barrageserver.common.base.BaseQueue;
import cn.tzmedia.barrageserver.common.manager.TimerManager;
import cn.tzmedia.barrageserver.common.utils.BaseUtils;
import cn.tzmedia.barrageserver.message.MessageProducer;
import cn.tzmedia.barrageserver.message.entity.BaseResponseMsg;
import cn.tzmedia.barrageserver.message.entity.BaseResponseRootMsg;
import cn.tzmedia.barrageserver.message.iface.IMsgQueue;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/12.
 */
@Log4j2
@Component
public abstract class MessageQueue extends BaseQueue<BaseResponseRootMsg> implements IMsgQueue {
    @Autowired
    private TenActiveService tenActiveService;
    @Autowired
    private MessageProducer messageProducer;

    @Override
    public void registerQueue() {
        messageProducer.registerObserver(this);
    }

    @Override
    public int sendMsg(BaseResponseRootMsg responseMsg) {
        return tenActiveService.sendMsg(responseMsg.getShopId(),responseMsg);
    }

    @Override
    public void sendMsgAsync(BaseResponseRootMsg responseMsg,boolean needSingleExecutor) {
        tenActiveService.sendMsgAsync(responseMsg.getShopId(),responseMsg,needSingleExecutor,responseMsg.getShopId(),0);
    }

    @Override
    public void sendMsgAsync(BaseResponseRootMsg responseMsg, String key, boolean needSingleExecutor) {
        tenActiveService.sendMsgAsync(responseMsg.getShopId(),responseMsg,needSingleExecutor,key,0);
    }

    @Override
    public void sendMsg(List<BaseResponseRootMsg> responseMsgList) {
        if(null == responseMsgList){
            return;
        }
        for(BaseResponseRootMsg rootMsg:responseMsgList){
            sendMsg(rootMsg);
        }
    }

    @Override
    public void sendMsgAsync(List<BaseResponseRootMsg> responseMsgList, boolean needSingleExecutor) {
        if(null == responseMsgList){
            return;
        }
        for(BaseResponseRootMsg rootMsg:responseMsgList){
            if(!needSingleExecutor){
                //此处注意，有特殊处理
                //如果不是单线程池，不能一次性全部发送，因为腾讯云IM不能一次性处理过多消息，有每秒100条的限制
                sendMsgAsync(rootMsg,false);
                //消息队列的处理必须处于IO线程池，如果不是，这里可能会造成一定的卡顿
                BaseUtils.sleep(50);
            }else{
                sendMsgAsync(rootMsg,true);
            }
        }
    }

}
