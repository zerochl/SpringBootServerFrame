package cn.tzmedia.barrageserver.dbserver.repository;

import cn.tzmedia.barrageserver.dbserver.model.HotCreatorTable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/18.
 */
@Repository
public interface HotCreatorRepository extends MongoRepository<HotCreatorTable,String>{
    @Query(fields = "{'_id':1,'activityid':1,'usertoken':1,'points':1,'teamCode':1,'user.nickname':1,'user.level':1,'user.levelRange':1,'user.image':1}")
    List<HotCreatorTable> findAllByActivityIdOrderByHotDesc(String activityId);
    @Query(fields = "{'_id':1,'activityid':1,'usertoken':1,'points':1,'teamCode':1,'user.nickname':1,'user.level':1,'user.levelRange':1,'user.image':1}")
    List<HotCreatorTable> findTopByActivityIdOrderByHotDesc(Pageable pageable, String activityId);
}
