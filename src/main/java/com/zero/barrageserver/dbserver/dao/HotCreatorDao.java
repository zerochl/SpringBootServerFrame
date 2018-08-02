package com.zero.barrageserver.dbserver.dao;

import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.dbserver.model.HotCreatorTable;
import com.zero.barrageserver.dbserver.repository.HotCreatorRepository;
import com.zero.barrageserver.dbserver.model.HotCreatorTable;
import com.zero.barrageserver.dbserver.repository.HotCreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/18.
 */
@Component
public class HotCreatorDao {
    @Autowired
    private HotCreatorRepository hotCreatorRepository;

    public List<HotCreatorTable> getAllHotCreator(String activityId){
        return hotCreatorRepository.findAllByActivityIdOrderByHotDesc(activityId);
    }

    public List<HotCreatorTable> getTopCreator(String activityId){
        return hotCreatorRepository.findTopByActivityIdOrderByHotDesc(new PageRequest(0, Constant.HOT_POINTS_USER_MAX_COUNT),activityId);
    }
}
