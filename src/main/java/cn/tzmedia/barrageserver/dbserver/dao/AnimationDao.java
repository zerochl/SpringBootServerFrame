package cn.tzmedia.barrageserver.dbserver.dao;

import cn.tzmedia.barrageserver.common.constant.CacheConstant;
import cn.tzmedia.barrageserver.dbserver.model.AnimationTable;
import cn.tzmedia.barrageserver.dbserver.repository.AnimationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/15.
 */
@Component
@Log4j2
public class AnimationDao {
    @Autowired
    private AnimationRepository animationRepository;

    @Cacheable(value = CacheConstant.CACHE_TABLE_ANIMATION,key = CacheConstant.CACHE_ANIMATION_AWARD)
    public List<AnimationTable> getAllAwardAnimation(){
        log.info("get from db.");
        return animationRepository.findAllByAwardIsTrueOrderByAwardRuleDesc();
    }

    /**
     * 获取所有动画文件
     * @return
     */
    public List<AnimationTable> getAllAnimation(){
        return animationRepository.findAllByIdIsNotNull();
    }
}
