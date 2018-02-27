package cn.tzmedia.barrageserver.dbserver.dao;

import cn.tzmedia.barrageserver.common.constant.Constant;
import cn.tzmedia.barrageserver.dbserver.model.HotCreatorTable;
import cn.tzmedia.barrageserver.dbserver.repository.HotCreatorRepository;
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
