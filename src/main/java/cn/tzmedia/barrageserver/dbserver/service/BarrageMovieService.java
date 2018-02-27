package cn.tzmedia.barrageserver.dbserver.service;

import cn.tzmedia.barrageserver.common.constant.ErrorConstant;
import cn.tzmedia.barrageserver.common.entity.apirequest.ApiReqBarrageMovie;
import cn.tzmedia.barrageserver.common.entity.response.BaseResponseEntity;
import cn.tzmedia.barrageserver.common.utils.StringUtil;
import cn.tzmedia.barrageserver.dbwrite.entity.BaseDBWriteEntity;
import cn.tzmedia.barrageserver.dbserver.dao.BarrageMovieDao;
import cn.tzmedia.barrageserver.dbserver.model.BarrageMovieTable;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2018/1/8.
 */
@Service
@Log4j2
public class BarrageMovieService extends BaseDBWriteService<BarrageMovieTable>{
    @Autowired
    private BarrageMovieDao movieDao;
    public BaseResponseEntity<List<ApiReqBarrageMovie>> getAllBarrageMovie(){
        List<BarrageMovieTable> allMovie = movieDao.getAllMovie();
        if(null == allMovie || allMovie.size() == 0){
            return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OK);
        }
        ArrayList<ApiReqBarrageMovie> responseAllMovie = new ArrayList<>();
        for(BarrageMovieTable movieTable:allMovie){
            ApiReqBarrageMovie responseMovie = new ApiReqBarrageMovie(movieTable);
            responseAllMovie.add(responseMovie);
        }
        return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OK,responseAllMovie);
    }

    public BaseResponseEntity updateOrInsertBarrageMovie(ApiReqBarrageMovie barrageMovie){
        if(null == barrageMovie){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
        return updateAndInsert(new BarrageMovieTable(barrageMovie));
    }

    @Override
    public BaseResponseEntity updateAndInsert(BarrageMovieTable barrageMovieTable) {
        if(null == barrageMovieTable){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
//        long startTime = System.nanoTime();
        BarrageMovieTable barrageOldMovieTable = null;
        if(!StringUtil.isEmpty(barrageMovieTable.getId())){
            barrageOldMovieTable = movieDao.getMovieById(barrageMovieTable.getId());
        }
        log.info("name:" + barrageMovieTable.getName());
        BarrageMovieTable result = null;
        //此处消息不需要保持一致性，所以可以使用队列插入
        if(null == barrageOldMovieTable){
            //需要执行insert操作
            barrageMovieTable.prepareForInsert();
            result = movieDao.insert(barrageMovieTable);
        }else{
            //需要执行update操作
            barrageOldMovieTable.reSetData(barrageMovieTable);
            result = movieDao.save(barrageOldMovieTable);
        }
//        log.error("更新设备结果:" + (System.nanoTime() - startTime) / 1000000);
        if(null == result){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OTHER);
        }else{
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OK);
        }
    }

    @Override
    public BaseResponseEntity delete(BarrageMovieTable barrageMovieTable) {
        return null;
    }

    @Override
    public BarrageMovieTable getEntity(BaseDBWriteEntity dbWriteEntity) {
        return JSONObject.parseObject(dbWriteEntity.getWriteValue(),BarrageMovieTable.class);
    }
}
