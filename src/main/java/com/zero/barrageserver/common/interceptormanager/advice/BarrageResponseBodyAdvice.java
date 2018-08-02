package com.zero.barrageserver.common.interceptormanager.advice;

import com.zero.barrageserver.common.annotation.SerializedField;
import com.zero.barrageserver.common.utils.BaseUtils;
import com.zero.barrageserver.common.utils.BaseUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;
import java.util.*;

@Order(2)
@Log4j2
//@ControllerAdvice(basePackages = "cn.tzmedia.barrageserver.api")
@RestControllerAdvice
public class BarrageResponseBodyAdvice implements ResponseBodyAdvice {
    //包含项
    private String[] includes = {};
    //排除项
    private String[] excludes = {};
    //是否加密
    private boolean encode = true;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //这里可以根据自己的需求
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //重新初始化为默认值
        includes = new String[]{};
        excludes = new String[]{};
        encode = true;

        //判断返回的对象是单个对象，还是list，活着是map
        if(o==null){
            return null;
        }
        if(methodParameter.getMethod().isAnnotationPresent(SerializedField.class)){
            //获取注解配置的包含和去除字段
            SerializedField serializedField = methodParameter.getMethodAnnotation(SerializedField.class);
            includes = serializedField.includes();
            excludes = serializedField.excludes();
            //是否加密
            encode = serializedField.encode();
            if(!serializedField.enable()){
                //不需要过滤，原样返回
                return o;
            }
        }else{
            return o;
        }
        return encodeObject(o);
    }

    private Object encodeObject(Object o){
        Map<String,Object> map = new HashMap<String, Object>();
        Object obj = null;
        if(o instanceof List){
            List<Object> retList = new ArrayList<>();
            for (Object item:(List)o){
                if(item instanceof String || item instanceof Integer){
                    obj = getNewVal(item,encode);
                }else{
                    obj = encodeObject(item);
                }
                retList.add(obj);
            }
            return retList;
        }
        Field[] fields = BaseUtils.getObjectAllFields(o);
        for(Field field:fields){
            if(field.getType() == Object.class || field.getType() == List.class){
                Object value = getNewVal(o, field,false);
                if(null == value){
                    obj = null;
                }else{
                    obj = encodeObject(getNewVal(o, field,false));
                }
            }else if(includes.length==0 && excludes.length==0){
                obj = getNewVal(o, field,encode);
            }else if(includes.length>0){
                //有限考虑包含字段
                if(BaseUtils.isStringInArray(field.getName(), includes)){
                    obj = getNewVal(o, field,encode);
                }else{
                    obj = null;
                }
            }else{
                //去除字段
                if(excludes.length>0){
                    if(!BaseUtils.isStringInArray(field.getName(), excludes)){
                        obj = getNewVal(o, field,encode);
                    }else{
                        obj = null;
                    }
                }
            }
            map.put(field.getName(), obj);
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
    private Object getNewVal(Object o, Field field,boolean encode){
        Object newVal = null;
        try {
            field.setAccessible(true);
            Object val = field.get(o);

            if(val!=null){
                if(encode){
                    newVal = BaseUtils.encode(val.toString());
                }else{
                    newVal = val;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return newVal;
    }

    private Object getNewVal(Object value,boolean encode){
        if(encode){
            return BaseUtils.encode(value.toString());
        }else{
            return value;
        }
    }
}
