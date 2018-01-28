package cn.tzmedia.barrageserver.dbwrite.base;

import cn.tzmedia.barrageserver.common.base.BaseQueue;
import cn.tzmedia.barrageserver.common.manager.ExecutorManager;
import cn.tzmedia.barrageserver.dbwrite.DBWriteProducer;
import cn.tzmedia.barrageserver.dbwrite.entity.BaseDBWriteEntity;
import cn.tzmedia.barrageserver.dbwrite.iface.IDBWriteQueue;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Created by zero大神 on 2017/12/20.
 */
@Log4j2
public abstract class BaseDBWriteQueue extends BaseQueue<BaseDBWriteEntity> implements IDBWriteQueue {
    @Autowired
    private DBWriteProducer dbWriteProducer;
    @Override
    public void registerQueue() {
        dbWriteProducer.registerObserver(this);
    }

    @Override
    public void handleSync(BaseDBWriteEntity dbWriteEntity) {
        doExecute(dbWriteEntity);
    }

    @Override
    public void handleAsync(final BaseDBWriteEntity dbWriteEntity) {
//lamda格式，请注意
        Observable.create((ObservableOnSubscribe<Integer>) observableEmitter -> {
            log.info("on subscribe:" + ";thread:" + Thread.currentThread().getId());
            //执行写入操作
            doExecute(dbWriteEntity);
        })
        //此存储操作不需要考虑先后顺序，快速消费即可
        .subscribeOn(Schedulers.from(ExecutorManager.getDbWriteExecutor()))
        .subscribe();
    }
}
