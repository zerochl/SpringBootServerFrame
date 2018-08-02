package com.zero.barrageserver.activeapi.mapper;

import com.zero.barrageserver.common.constant.ApiSupportConstant;
import com.zero.barrageserver.common.entity.servermsg.BaseRequestRootMsgEntity;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.servermsg.BaseRequestRootMsgEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zero大神 on 2017/12/12.
 */
public interface TestActiveMapper {
    @POST(ApiSupportConstant.API_SERVER_PREFIX + ApiSupportConstant.PUSH_MESSAGE)
    Call<BaseResponseEntity> sendMessage(@Body BaseRequestRootMsgEntity requestEntity);
}
