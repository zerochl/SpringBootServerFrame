package cn.tzmedia.barrageserver.common.entity.servermsg;

import cn.tzmedia.barrageserver.server.model.ShowHistoryTable;
import lombok.Data;

/**
 * 接收的消息value为字符串格式json
 * Created by zero大神 on 2017/12/12.
 */
@Data
public class BaseRequestRootMsgEntity {
    private String msgtype;
    private String value;
    private String activityid;//群ID
    private String commentId;
    private String _id;

    public BaseRequestRootMsgEntity(){}
    public BaseRequestRootMsgEntity(ShowHistoryTable showHistoryTable){
        this.msgtype = showHistoryTable.getMsgType();
        this.value = showHistoryTable.getValue();
        this.activityid  = showHistoryTable.getActivityId();
        this._id = showHistoryTable.getId();
    }
}
