package cn.tzmedia.barrageserver.activeapi.dao;

import cn.tzmedia.barrageserver.activeapi.mapper.ServerActiveMapper;
import cn.tzmedia.barrageserver.common.entity.activeresponse.BaseActiveResponseEntity;
import cn.tzmedia.barrageserver.common.entity.activerequest.HomePageBaseEntity;
import cn.tzmedia.barrageserver.common.utils.HttpUtil;
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
