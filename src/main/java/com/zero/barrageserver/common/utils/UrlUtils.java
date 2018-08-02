package com.zero.barrageserver.common.utils;

import com.zero.barrageserver.common.constant.ApiConstant;
import com.zero.barrageserver.common.constant.Constant;
import lombok.extern.log4j.Log4j2;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Url 工具类
 * Created by Silence on 2016/11/24.
 */
@Log4j2
public class UrlUtils {

    public static void main(String[] args) throws Exception {
        System.err.println(getRealUrl("/images/eef110e9-0e3a-4a70-8b0a-755d82b73ece.jpeg"));
        System.err.println(getRealUrl("http://121.201.14.212/images/eef110e9-0e3a-4a70-8b0a-755d82b73ece.jpeg"));
        System.err.println(getRealUrl("http://qzapp.qlogo.cn/qzapp/1104826153/C84A322A215A074D56AD7857CEF56377/100"));
    }

    public static String getRealUrl(String url) {
        if(StringUtil.isEmpty(url)){
            return "";
        }
        URI uri = URI.create(url);
        if(StringUtil.isEmpty(uri.getHost()) || uri.getHost().startsWith("121.201.14.212")){
            return ApiConstant.RELEASE_IMAGE_HOST + (uri.getPath().startsWith("/") ? uri.getPath().substring(1) : uri.getPath());
        }else{
            return url;
        }
//        if(ApiConstant.QINIU_IMAGE_HOST.contains(uri.getHost())){
//            return url;
//        }else{
//            return ApiConstant.RELEASE_IMAGE_HOST + (uri.getPath().startsWith("/") ? uri.getPath().substring(1) : uri.getPath());
//        }
    }

    public static List<String> getRealUrl(List<String> urlList){
        if(null == urlList){
            return null;
        }
        List<String> realUrlList = new ArrayList<>();
        for(String url:urlList){
            realUrlList.add(getRealUrl(url));
        }
        return realUrlList;
    }

    public static String getDomain(String url) {
        String domain = "";
        try {
            URL target = new URL(url);
            domain = target.getHost();
        } catch (MalformedURLException e) {
            log.error("Url(" + url + ") Cannot convert to BASIC-URL");
            e.printStackTrace();
        }
        return domain;
    }

    public static String getTopDomain(String url) {
        String domain = "";
        try {
            URL target = new URL(url);
            domain = target.getHost();
            String[] part = domain.split("\\.");
            if (part.length > 2) {
                return part[part.length - 2] + "." + part[part.length - 1];
            }
        } catch (MalformedURLException e) {
            log.error("Url(" + url + ") Cannot convert to BASIC-URL");
            e.printStackTrace();
        }
        return domain;
    }

    public static String adjustUrl(String url) {
        if (url.contains("http://")) {
            return url;
        }
        return "http://" + url;
    }

}
