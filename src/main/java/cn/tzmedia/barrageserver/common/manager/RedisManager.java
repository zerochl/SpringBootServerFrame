package cn.tzmedia.barrageserver.common.manager;

import cn.tzmedia.barrageserver.common.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by zero大神 on 2017/11/27.
 */
@Component
@AllArgsConstructor
@Log4j2
public class RedisManager {
    private final StringRedisTemplate stringRedisTemplate;
    public String getCacheWithHashWithIndirect(String firstTable,String secondTable,String key){
        String nextKey = (String) stringRedisTemplate.opsForHash().get(firstTable,key);
        if(StringUtil.isEmpty(nextKey)){
            return null;
        }
        return (String) stringRedisTemplate.opsForHash().get(secondTable,nextKey);
    }

    public String getCacheWithHash(String table,String key){
        return (String) stringRedisTemplate.opsForHash().get(table,key);
    }

    public List<String> getCacheHashTable(String table){
        Map<Object,Object> set = stringRedisTemplate.boundHashOps(table).entries();
        Iterator<Map.Entry<Object, Object>> it = set.entrySet().iterator();
        ArrayList<String> resultList = new ArrayList<>();
        while(it.hasNext()){
            Map.Entry<Object, Object> entry = it.next();
            resultList.add(entry.getValue().toString());
        }
        log.info("redis cache write size:" + resultList.size());
        return resultList;
    }

    public void setCacheWithHash(String table,String key,String value){
        stringRedisTemplate.opsForHash().put(table,key,value);
    }

    public void deleteHash(String table,String key){
        stringRedisTemplate.opsForHash().delete(table,key);
    }

    public void delete(String folder,String key){
//        stringRedisTemplate.delete("tab-shop-activity-shopId:55c43920bdf67cc961e53992");
        stringRedisTemplate.delete(folder + ":" + key);
    }

    public void deletePrefix(String folder,String prefix){
        Set<String> set = stringRedisTemplate.keys(folder + ":" + prefix +"*");
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String keyStr = it.next();
            log.info("start delete key:" + keyStr);
            stringRedisTemplate.delete(keyStr);
        }
    }

    public void deleteFolder(String folder){
        Set<String> set = stringRedisTemplate.keys(folder +"*");
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String keyStr = it.next();
            log.info("start delete key:" + keyStr);
            stringRedisTemplate.delete(keyStr);
        }
    }
}
