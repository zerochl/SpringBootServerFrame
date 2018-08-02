package com.zero.barrageserver.dbserver.repository;

import com.zero.barrageserver.dbserver.model.ArtistTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/15.
 */
@Repository
public interface ArtistRepository extends MongoRepository<ArtistTable,String>{
    @Query(fields = "{'_id':1,'dmimage':1,'image':1,'introduce':1,'experience':1,'name':1,'sign':1,'tag':1}")
    List<ArtistTable> findAllByIdIn(List<String> ids);
}
