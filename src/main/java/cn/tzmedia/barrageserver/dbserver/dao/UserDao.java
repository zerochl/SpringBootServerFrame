package cn.tzmedia.barrageserver.dbserver.dao;

import cn.tzmedia.barrageserver.common.constant.CacheConstant;
import cn.tzmedia.barrageserver.dbserver.model.UserTable;
import cn.tzmedia.barrageserver.dbserver.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

/**
 * Created by zero大神 on 2017/11/28.
 */
@Component
@Log4j2
public class UserDao {
    @Autowired
    private UserRepository userRepository;

    @Caching(
            cacheable = {
                    @Cacheable(value = CacheConstant.CACHE_TABLE_USER_ID, key = "#id")
            }
//            ,
//            put = {
//                    @CachePut(value = CacheConstant.CACHE_TABLE_USER_USER_ID, key = "#result.userId + \"\"", condition = "#root.caches[0] == null and #result != null")
//            }
    )
    public UserTable getUserById(String id) {
        log.info("get from db");//566a437a149cc4fa752e8b91
        return userRepository.findById(id);
    }

    @Cacheable(value = CacheConstant.CACHE_TABLE_USER_USER_ID, key = "#userId + \"\"")
    public UserTable getUserByUserId(int userId) {
        log.info("get from db");
        return userRepository.findByUserId(userId);
    }
//    public UserTable getUserById(String id){
//        String userStr = redisManager.getCacheWithHash(CacheConstant.CACHE_TABLE_USER_USER_ID,id);
//        if(!StringUtil.isEmpty(userStr)){
//            return JSONObject.parseObject(userStr,UserTable.class);
//        }
//        UserTable user = userRepository.findById(id);
//        if(null == user){
//            return null;
//        }
//        userStr = JSONObject.toJSONString(user);
//        redisManager.setCacheWithHash(CacheConstant.CACHE_TABLE_USER_ID,id,userStr);
//        redisManager.setCacheWithHash(CacheConstant.CACHE_TABLE_USER_USER_ID,user.getUserId() + "",id);
//        return user;
//    }
//
////    @Cacheable(value = "userId",key = "#userId + \"\"")
//    public UserTable getUserByUserId(int userId){
//        log.info("get from db");
//        String userStr = redisManager.getCacheWithHashWithIndirect(CacheConstant.CACHE_TABLE_USER_USER_ID,CacheConstant.CACHE_TABLE_USER_ID,userId+"");
//        if(!StringUtil.isEmpty(userStr)){
//            return JSONObject.parseObject(userStr,UserTable.class);
//        }
//        return userRepository.findByUserId(userId);
//    }
}
