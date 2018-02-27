package cn.tzmedia.barrageserver.dbserver.dao;

import cn.tzmedia.barrageserver.common.constant.CacheConstant;
import cn.tzmedia.barrageserver.dbserver.model.BarrageMovieTable;
import cn.tzmedia.barrageserver.dbserver.repository.BarrageMovieRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zero大神 on 2018/1/8.
 */
@Component
@Log4j2
public class BarrageMovieDao {
    @Autowired
    private BarrageMovieRepository movieRepository;

    @Cacheable(value = CacheConstant.CACHE_TABLE_SHOP_BARRAGE_MOVIE,key = "\'"+CacheConstant.CACHE_KEY_ALL_SHOP_BARRAGE_MOVIE+"\'")
    public List<BarrageMovieTable> getAllMovie(){
        log.info("in get all movie get from db");
        return movieRepository.findAllByVideoUrlIsNotNull();
    }
    @Cacheable(value = CacheConstant.CACHE_TABLE_SHOP_BARRAGE_MOVIE,key = "#id")
    public BarrageMovieTable getMovieById(String id){
        log.info("getMovieById get from db");
        return movieRepository.findById(id);
    }

    @Caching(
            put = {
                    @CachePut(value = CacheConstant.CACHE_TABLE_SHOP_BARRAGE_MOVIE,key = "#result.id")
            },
            evict = {
                    @CacheEvict(value = CacheConstant.CACHE_TABLE_SHOP_BARRAGE_MOVIE,key = "\'"+CacheConstant.CACHE_KEY_ALL_SHOP_BARRAGE_MOVIE+"\'")
            }
    )
    public BarrageMovieTable insert(BarrageMovieTable barrageMovieTable){
        return movieRepository.insert(barrageMovieTable);
    }

    @Caching(
            put = {
                    @CachePut(value = CacheConstant.CACHE_TABLE_SHOP_BARRAGE_MOVIE,key = "#result.id"),
            },
            evict = {
                    @CacheEvict(value = CacheConstant.CACHE_TABLE_SHOP_BARRAGE_MOVIE,key = "\'"+CacheConstant.CACHE_KEY_ALL_SHOP_BARRAGE_MOVIE+"\'")
            }
    )
    public BarrageMovieTable save(BarrageMovieTable barrageMovieTable){
        return movieRepository.save(barrageMovieTable);
    }
}
