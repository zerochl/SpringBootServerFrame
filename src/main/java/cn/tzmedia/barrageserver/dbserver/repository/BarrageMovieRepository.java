package cn.tzmedia.barrageserver.dbserver.repository;

import cn.tzmedia.barrageserver.dbserver.model.BarrageMovieTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zero大神 on 2018/1/8.
 */
@Repository
public interface BarrageMovieRepository extends MongoRepository<BarrageMovieTable,String>{
    @Query(fields = "{'_id':1,'thumbnail':1,'name':1,'video_url':1,'video_res_id':1}")
    List<BarrageMovieTable> findAllByVideoUrlIsNotNull();
    @Query(fields = "{'_id':1,'thumbnail':1,'name':1,'video_url':1,'video_res_id':1,'create_time':1,'update_time':1}")
    BarrageMovieTable findById(String id);
}
