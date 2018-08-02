package com.zero.barrageserver.common.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.zero.barrageserver.common.constant.ApiConstant;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit2 http帮助类
 */
@Component
public class HttpUtil {
    private static Retrofit retrofitServer;
    private static Retrofit retrofitCMS;
    private static Retrofit retrofitTen;
    private static Retrofit retrofitTest;
    static {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Builder builder = chain.request().newBuilder();
                /*String headString = HttpAccessConstant.getNewHeadString();
                builder.addHeader("X-APP-AUTHORIZATION", headString);
                if (!StringUtils.isEmpty(BaseApplication.getInstance().getTokenId())) {
                    L.d("*************************");
                    L.d("****** token: " + BaseApplication.getInstance().getTokenId() + "**********");
                    L.d("*************************");
                    builder.addHeader("X-APP-TOKEN", BaseApplication.getInstance().getTokenId());
                }*/

                Request request = builder.build();
                Response response = chain.proceed(request);
                return response;
            }
        };

        HttpLoggingInterceptor netinterceptor = new HttpLoggingInterceptor();
        netinterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add the interceptormanager to OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES).retryOnConnectionFailure(false)//.addNetworkInterceptor(interceptor)
                .addInterceptor(netinterceptor).build();

        retrofitServer = new Retrofit.Builder().baseUrl(ApiConstant.RELEASE_SERVER_HOST)
                .addConverterFactory(GsonConverterFactory.create()).client(client).build();
        retrofitCMS = new Retrofit.Builder().baseUrl(ApiConstant.RELEASE_CMS_HOST)
                .addConverterFactory(GsonConverterFactory.create()).client(client).build();
        retrofitTen = new Retrofit.Builder().baseUrl(ApiConstant.RELEASE_TEN_HOST)
                .addConverterFactory(GsonConverterFactory.create()).client(client).build();
        retrofitTest = new Retrofit.Builder().baseUrl(ApiConstant.TEST_HOST)
                .addConverterFactory(GsonConverterFactory.create()).client(client).build();

    }

    /**
     * Server client
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T getServerService(Class<T> c) {
        return retrofitServer.create(c);
    }

    /**
     * CMS client
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T getCMSService(Class<T> c) {
        return retrofitCMS.create(c);
    }

    /**
     * Ten client
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T getTenService(Class<T> c) {
        return retrofitTen.create(c);
    }

    /**
     * test
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T getTestService(Class<T> c) {
        return retrofitTest.create(c);
    }

}
