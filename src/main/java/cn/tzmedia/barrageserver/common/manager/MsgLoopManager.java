package cn.tzmedia.barrageserver.common.manager;

import cn.tzmedia.barrageserver.activeapi.service.TenActiveService;
import cn.tzmedia.barrageserver.common.constant.Constant;
import cn.tzmedia.barrageserver.message.entity.BaseResponseRootMsg;
import cn.tzmedia.barrageserver.dbserver.service.LoopMessageService;
import cn.tzmedia.barrageserver.dbserver.service.BarrageConfigService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by zero大神 on 2017/12/22.
 */
@Component
@AllArgsConstructor
@Log4j2
public class MsgLoopManager {
    private LoopMessageService loopMessageService;
    private TimerManager timerManager;
    private BarrageConfigService barrageConfigService;
    private TenActiveService tenActiveService;
    public void addLoopMsg(BaseResponseRootMsg msg){
        if(null == msg || barrageConfigService.getBarrageLoopIntervalTime(msg.getShopId()) == 0){
            return;
        }
        log.error("循环队列大小:" + timerManager.getFasterFutureCache().size());
        BaseResponseRootMsg loopMsg = loopMessageService.getLoopMsg(msg);
        if(null == loopMsg){
            return;
        }
        timerManager.postLoopMsg(getLoopRunnable(msg),barrageConfigService.getBarrageLoopIntervalTime(msg.getShopId()));
    }

    /**
     * 每个消息必须只能只有一个循环体
     * @param msg
     * @return
     */
    private Runnable getLoopRunnable(BaseResponseRootMsg msg){
        Iterator<Runnable> runableIt = timerManager.getFasterFutureCache().keySet().iterator();
        while (runableIt.hasNext()){
            Runnable item = runableIt.next();
            if(item instanceof LoopRunnable){
                if(((LoopRunnable)item).getOnlyKey().equals(msg.toNormalMsgString())){
                    log.error("是同一条消息");
                    return item;
                }
            }
        }
        return new LoopRunnable(msg);
    }

    private class LoopRunnable implements Runnable{
        BaseResponseRootMsg loopMsg;
        String onlyKey;
        public LoopRunnable(BaseResponseRootMsg loopMsg){
            this.loopMsg = loopMsg;
            this.onlyKey = loopMsg.toNormalMsgString();
        }
        @Override
        public void run() {
            //发送之前再次执行判断，如果不需要发送则停止此消息的循环
            BaseResponseRootMsg loopMsg = loopMessageService.getLoopMsg(this.loopMsg);
            if(null == loopMsg){
                return;
            }
            //使用单线程池同步发送,此处可能会有问题，例如每个店消息都很多，会造成单线程处理不过来，但是腾讯IM只使用了每秒100条，所以也不能放开随便发送
            tenActiveService.sendMsgAsync(loopMsg.getShopId(),loopMsg,true, Constant.SINGLE_THREAD_EXECUTOR_TYPE_LOOPMSG,0);
            timerManager.postLoopMsg(this,loopMsg.getLoopIntervalTime());
        }

        public String getOnlyKey(){
            return onlyKey;
        }
    }

}
