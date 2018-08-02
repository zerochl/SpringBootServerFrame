package com.zero.barrageserver.dbwrite.iface;

import com.zero.barrageserver.dbwrite.entity.BaseDBWriteEntity;
import com.zero.barrageserver.dbwrite.entity.BaseDBWriteEntity;

/**
 * Created by zero大神 on 2017/12/19.
 */
public interface DBWriteObserver {
    /**会接收所有消息，需要的才会执行push*/
    void receiveMessage(BaseDBWriteEntity msg);
}
