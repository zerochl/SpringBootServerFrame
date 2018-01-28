package cn.tzmedia.barrageserver.common.interceptormanager.advice;

import cn.tzmedia.barrageserver.common.annotation.RequestAdviceField;
import cn.tzmedia.barrageserver.common.utils.BaseUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zero大神 on 2017/11/28.
 */
@Order(1)
@Log4j2
//@ControllerAdvice(basePackages = "cn.tzmedia.barrageserver.api")
//@RestControllerAdvice
public class BarrageRequestBodyAdvice extends RequestBodyAdviceAdapter{
    //包含项
    private String[] includes = {};
    //排除项
    private String[] excludes = {};
    //是否加密
    private boolean encode = true;
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        log.info("request body:");
        return true;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        log.info("request body:" + body);
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        log.info("request body:");
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        log.info("request body:" + body);
        //重新初始化为默认值
        includes = new String[]{};
        excludes = new String[]{};
        encode = true;

        //判断返回的对象是单个对象，还是list，活着是map
        if(body == null){
            return body;
        }
        if(methodParameter.getMethod().isAnnotationPresent(RequestAdviceField.class)){
            //获取注解配置的包含和去除字段
            RequestAdviceField serializedField = methodParameter.getMethodAnnotation(RequestAdviceField.class);
            includes = serializedField.includes();
            excludes = serializedField.excludes();
            //是否加密
            encode = serializedField.encode();
        }
        Object retObj = null;
        if (body instanceof List){
            //List
            List list = (List)body;
            retObj = handleList(list);
        }else{
            //Single Object
            retObj = handleSingleObject(body);
        }
        log.info("request body:" + body);
        return body;
    }

    /**
     * 处理返回值是列表
     *
     * @param list
     * @return
     */
    private List handleList(List list){
        List retList = new ArrayList();
        for (Object o:list){
            Map map = (Map) handleSingleObject(o);
            retList.add(map);
        }
        return retList;
    }

    /**
     * 处理返回值是单个enity对象
     *
     * @param o
     * @return
     */
    private Object handleSingleObject(Object o){
        Map<String,Object> map = new HashMap<String, Object>();

        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field:fields){
            //如果未配置表示全部的都返回
            if(includes.length==0 && excludes.length==0){
                String newVal = getNewDecVal(o, field);
                map.put(field.getName(), newVal);
            }else if(includes.length>0){
                //有限考虑包含字段
                if(BaseUtils.isStringInArray(field.getName(), includes)){
                    String newVal = getNewDecVal(o, field);
                    map.put(field.getName(), newVal);
                }
            }else{
                //去除字段
                if(excludes.length>0){
                    if(!BaseUtils.isStringInArray(field.getName(), excludes)){
                        String newVal = getNewDecVal(o, field);
                        map.put(field.getName(), newVal);
                    }
                }
            }

        }
        return map;
    }

    /**
     * 获取加密后的新值
     *
     * @param o
     * @param field
     * @return
     */
    private String getNewDecVal(Object o, Field field){
        String newVal = "";
        try {
            field.setAccessible(true);
            Object val = field.get(o);

            if(val!=null){
                if(encode){
                    newVal = BaseUtils.decode(val.toString());
                }else{
                    newVal = val.toString();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return newVal;
    }
}
