package com.zero.barrageserver.dbserver.repository;

import com.zero.barrageserver.dbserver.model.AnimationTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/15.
 */
@Repository
public interface AnimationRepository extends MongoRepository<AnimationTable,String>{
    @Query(fields = "{'_id':1,'name':1,'rule':1}")
    List<AnimationTable> findAllByAwardIsTrueOrderByAwardRuleDesc();
    @Query(fields = "{'_id':1,'name':1,'image':1}")
    List<AnimationTable> findAllByIdIsNotNull();
}
