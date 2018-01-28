package cn.tzmedia.barrageserver.activeapi.dao;

import cn.tzmedia.barrageserver.activeapi.mapper.TestActiveMapper;
import cn.tzmedia.barrageserver.common.entity.servermsg.BaseRequestRootMsgEntity;
import cn.tzmedia.barrageserver.common.entity.response.BaseResponseEntity;
import cn.tzmedia.barrageserver.common.utils.HttpUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by zero大神 on 2017/12/12.
 */
@Component
@Log4j2
public class TestActiveDao {
    private TestActiveMapper testActiveMapper = HttpUtil.getTestService(TestActiveMapper.class);

    public BaseResponseEntity sendMsg(BaseRequestRootMsgEntity requestMsgEntity){
        if(null == requestMsgEntity){
            return null;
        }
        Response<BaseResponseEntity> response = null;
        try {
            response = testActiveMapper.sendMessage(requestMsgEntity).execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
