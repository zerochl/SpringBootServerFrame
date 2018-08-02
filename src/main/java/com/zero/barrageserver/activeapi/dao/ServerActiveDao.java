package com.zero.barrageserver.activeapi.dao;

import com.zero.barrageserver.activeapi.mapper.ServerActiveMapper;
import com.zero.barrageserver.common.entity.activeresponse.BaseActiveResponseEntity;
import com.zero.barrageserver.common.entity.activerequest.HomePageBaseEntity;
import com.zero.barrageserver.common.utils.HttpUtil;
import com.zero.barrageserver.activeapi.mapper.ServerActiveMapper;
import com.zero.barrageserver.common.entity.activerequest.HomePageBaseEntity;
import com.zero.barrageserver.common.utils.HttpUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/1.
 */
@Component
@Log4j2
public class ServerActiveDao {
    private ServerActiveMapper serverActiveMapper = HttpUtil.getServerService(ServerActiveMapper.class);
    public List<HomePageBaseEntity> getHomePageInfoList(){
        try {
            Response<BaseActiveResponseEntity<List<HomePageBaseEntity>>> response = serverActiveMapper.getHomeSelectInfo().execute();
            log.info("response home:" + response.body().getResult());
            return response.body().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
