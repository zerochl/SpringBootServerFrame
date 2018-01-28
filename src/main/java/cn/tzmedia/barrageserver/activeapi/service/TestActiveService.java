package cn.tzmedia.barrageserver.activeapi.service;

import cn.tzmedia.barrageserver.activeapi.dao.TestActiveDao;
import cn.tzmedia.barrageserver.common.constant.ErrorConstant;
import cn.tzmedia.barrageserver.common.entity.servermsg.BaseRequestRootMsgEntity;
import cn.tzmedia.barrageserver.common.entity.response.BaseResponseEntity;
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
