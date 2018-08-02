package com.zero.barrageserver.common.interceptormanager.interceptor;

import lombok.extern.log4j.Log4j2;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by zero大神 on 2017/11/30.
 */
@Log4j2
public class CMSInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
//        String token = CacheFacade.getObject(Constants.IM.TOKEN);
        Request request = originalRequest
                .newBuilder()
//                .addHeader(Api.Http.AUTHORIZATION, "Bearer " + token)
                .build();
        return chain.proceed(request);
    }
}
