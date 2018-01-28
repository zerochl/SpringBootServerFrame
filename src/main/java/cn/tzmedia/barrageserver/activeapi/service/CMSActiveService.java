package cn.tzmedia.barrageserver.activeapi.service;

import cn.tzmedia.barrageserver.activeapi.dao.CMSActiveDao;
import cn.tzmedia.barrageserver.common.entity.activerequest.DownloadImageEntity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zero大神 on 2017/11/30.
 */
@Deprecated
@Service
@Log4j2
public class CMSActiveService {
    @Autowired
    private CMSActiveDao cmsActiveDao;

    public void getDownloadInfoList(Consumer<List<DownloadImageEntity>> next,Consumer<Throwable> error){
        Observable.create(new ObservableOnSubscribe<List<DownloadImageEntity>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<DownloadImageEntity>> observableEmitter) throws Exception {
                        log.info("on subscribe:" + ";thread:" + Thread.currentThread().getId());
                        observableEmitter.onNext(cmsActiveDao.getDownloadInfoList());
                    }
                })
                .subscribeOn(Schedulers.io())//执行在一个新的线程
                .subscribe(next, error);
    }

}
