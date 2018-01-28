package cn.tzmedia.barrageserver.message.iface;

import cn.tzmedia.barrageserver.message.entity.BaseResponseMsg;
import cn.tzmedia.barrageserver.message.entity.BaseResponseRootMsg;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/12.
 */
public interface IMsgQueue{

    int sendMsg(BaseResponseRootMsg responseMsg);
    void sendMsgAsync(BaseResponseRootMsg responseMsg, boolean needSingleExecutor);
    void sendMsgAsync(BaseResponseRootMsg responseMsg,String key, boolean needSingleExecutor);

    void sendMsg(List<BaseResponseRootMsg> responseMsgList);
    void sendMsgAsync(List<BaseResponseRootMsg> responseMsgList, boolean needSingleExecutor);
}
