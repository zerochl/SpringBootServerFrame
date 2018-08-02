package com.zero.barrageserver.dbserver.repository;

import com.zero.barrageserver.dbserver.model.UserTable;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by zero大神 on 2017/11/27.
 */
@Repository
public interface UserRepository extends MongoRepository<UserTable,ObjectId> {
    UserTable findById(String id);
    UserTable findByUserId(int userid);
//    //模仿数据
//    private static List<UserTable> users = new ArrayList<UserTable>();
//
//    static {
//        //初始化User数据
//        for (int i=0;i<10;i++){
//            UserTable user = new UserTable();
//            user.setId(i);
//            user.setEmail("email" + i);
//            user.setPassword("password" + i);
//
//            users.add(user);
//        }
//    }
//    public UserTable getUserById(int id){
//        for (UserTable user : users){
//            if(user.getId() == id){
//                return user;
//            }
//        }
//        return  null;
//    }

}
