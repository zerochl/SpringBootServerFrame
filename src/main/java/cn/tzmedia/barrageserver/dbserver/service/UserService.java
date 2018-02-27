package cn.tzmedia.barrageserver.dbserver.service;

import cn.tzmedia.barrageserver.dbserver.dao.UserDao;
import cn.tzmedia.barrageserver.dbserver.model.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zero大神 on 2017/11/27.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public UserTable getUserById(String id){
        return userDao.getUserById(id);
    }

    public UserTable getUserByUserId(int userId){
        return userDao.getUserByUserId(userId);
    }
}
