package com.zero.barrageserver.common.interceptormanager.interceptor;


import lombok.extern.log4j.Log4j2;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by zero大神 on 2017/11/30.
 */
@Component
@Log4j2
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        log.info(String.format("发送请求：%s%n请求头：%s",request.url(), request.headers()));
        Response response = chain.proceed(request);
        return response;
    }
}
