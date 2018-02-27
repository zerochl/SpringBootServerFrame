package cn.tzmedia.barrageserver.dbserver.dao;

import cn.tzmedia.barrageserver.dbserver.model.ArtistTable;
import cn.tzmedia.barrageserver.dbserver.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/15.
 */
@Component
public class ArtistDao {
    @Autowired
    private ArtistRepository artistRepository;

    public List<ArtistTable> getArtistListByIdList(List<String> idList){
        if(null == idList || 0 == idList.size()){
            return null;
        }
        return artistRepository.findAllByIdIn(idList);
    }
}
