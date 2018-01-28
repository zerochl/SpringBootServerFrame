package cn.tzmedia.barrageserver.dbwrite.iface;

import cn.tzmedia.barrageserver.dbwrite.entity.BaseDBWriteEntity;

/**
 * Created by zero大神 on 2017/12/20.
 */
public interface IDBWriteQueue{
    void doExecute(BaseDBWriteEntity dbWriteEntity);
    void handleSync(BaseDBWriteEntity dbWriteEntity);
    void handleAsync(BaseDBWriteEntity dbWriteEntity);
}
