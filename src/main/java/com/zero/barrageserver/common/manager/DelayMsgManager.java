package com.zero.barrageserver.common.manager;

import com.zero.barrageserver.activeapi.service.TenActiveService;
import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.message.entity.BaseResponseRootMsg;
import com.zero.barrageserver.activeapi.service.TenActiveService;
import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.message.entity.BaseResponseRootMsg;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by zero大神 on 2018/1/4.
 */

@Component
@AllArgsConstructor
@Log4j2
public class DelayMsgManager {
    private TimerManager timerManager;
    private TenActiveService tenActiveService;
    public void addDelayMsg(BaseResponseRootMsg msg){
        if(null == msg){
            return;
        }
        Runnable hotMsgRun = getDelayRunnable(msg);
        if(null != hotMsgRun){
            //能获取到Runnable说明产生了新的，需要post
            timerManager.postFaster(hotMsgRun, Constant.NORMAL_DELAY_SEND);
        }
    }

    /**
     * 每个消息必须只能只有一个循环体
     * @param msg
     * @return
     */
    private Runnable getDelayRunnable(BaseResponseRootMsg msg){
        Iterator<Runnable> runableIt = timerManager.getFasterFutureCache().keySet().iterator();
        while (runableIt.hasNext()){
            Runnable item = runableIt.next();
            if(item instanceof DelayRunnable){
                if(((DelayRunnable)item).getOnlyKey().equals(msg.getActivityId())){
                    log.info("是同一个节目的消息");
                    //同一个节目，只需要更新数据，不需要重新post
                    ((DelayRunnable) item).setMsg(msg);
                    return null;
                }
            }
        }
        return new DelayRunnable(msg);
    }

    private class DelayRunnable implements Runnable{
        BaseResponseRootMsg hotMsg;
        String onlyKey;
        public DelayRunnable(BaseResponseRootMsg hotMsg){
            this.hotMsg = hotMsg;
            this.onlyKey = hotMsg.getActivityId();
        }

        public void setMsg(BaseResponseRootMsg hotMsg){
            this.hotMsg = hotMsg;
        }

        @Override
        public void run() {
            //使用单线程池同步发送,此处可能会有问题，例如每个店消息都很多，会造成单线程处理不过来，但是腾讯IM只使用了每秒100条，所以也不能放开随便发送
            //热度消息也需要顺序，所以此处使用单线程池
            tenActiveService.sendMsgAsync(hotMsg.getShopId(),hotMsg,true, Constant.SINGLE_THREAD_EXECUTOR_TYPE_HOTMSG,0);
            timerManager.getFasterFutureCache().remove(this);
        }

        public String getOnlyKey(){
            return onlyKey;
        }
    }
}
