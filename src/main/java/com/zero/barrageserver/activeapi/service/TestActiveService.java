package com.zero.barrageserver.activeapi.service;

import com.zero.barrageserver.activeapi.dao.TestActiveDao;
import com.zero.barrageserver.common.constant.ErrorConstant;
import com.zero.barrageserver.common.entity.servermsg.BaseRequestRootMsgEntity;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.servermsg.BaseRequestRootMsgEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zero大神 on 2017/12/12.
 */
@Service
@Log4j2
public class TestActiveService {
    @Autowired
    private TestActiveDao testActiveDao;

    public BaseResponseEntity sendMsg(BaseRequestRootMsgEntity requestMsgEntity){
        BaseResponseEntity activeResponseEntity = testActiveDao.sendMsg(requestMsgEntity);
        if(null == activeResponseEntity){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OTHER);
        }else{
            return activeResponseEntity;
        }
    }
}
