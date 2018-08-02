package com.zero.barrageserver.dbserver.service;

import com.zero.barrageserver.common.constant.CacheConstant;
import com.zero.barrageserver.common.constant.ErrorConstant;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.manager.RedisManager;
import com.zero.barrageserver.dbserver.dao.AnimationDao;
import com.zero.barrageserver.dbserver.model.AnimationTable;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/15.
 */
@Service
public class AnimationService {
    @Autowired
    private AnimationDao animationDao;
    @Autowired
    private RedisManager redisManager;

    public BaseResponseEntity clearAnimationCache(){
        redisManager.deleteFolder(CacheConstant.CACHE_TABLE_ANIMATION);
        return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OK);
    }

    public List<AnimationTable> getDownloadAnimFileList(){
        return animationDao.getAllAnimation();
    }

//    /**
//     * 重新装备赞赏消息，如果符合动画条件需要赋予值
//     * @return
//     */
//    public ResponseAwardMsg equipAwardMsg(ResponseAwardMsg responseAwardMsg){
//        if(null == responseAwardMsg){
//            return null;
//        }
//        List<AnimationTable> awardList = animationDao.getAllAwardAnimation();
//        if(null == awardList || 0 == awardList.size()){
//            return responseAwardMsg;
//        }
//        //AwardList根据AwardRule做了倒序排序
//        for(AnimationTable animationTable:awardList){
//            if(animationTable.getAwardRule() <= responseAwardMsg.getPrice()){
//
//                break;
//            }
//        }
//    }
}
