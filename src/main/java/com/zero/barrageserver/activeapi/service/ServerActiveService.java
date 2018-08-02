package com.zero.barrageserver.activeapi.service;

import com.zero.barrageserver.activeapi.dao.ServerActiveDao;
import com.zero.barrageserver.common.entity.activerequest.HomePageBaseEntity;
import com.zero.barrageserver.common.entity.activerequest.HomePageBaseEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zero大神 on 2017/11/30.
 */
@Deprecated
@Service
@Log4j2
public class ServerActiveService {
    @Autowired
    private ServerActiveDao serverActiveDao;

    public List<HomePageBaseEntity> getHomePageInfoList(){
        return serverActiveDao.getHomePageInfoList();
    }

}
