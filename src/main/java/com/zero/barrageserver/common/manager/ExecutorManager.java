package com.zero.barrageserver.common.manager;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by zero大神 on 2017/12/1.
 */
@Component
public class ExecutorManager {

    private static HashMap<String,Executor> singleExecutorMap = new HashMap<>();
    private static Executor normalMsgExecutor = Executors.newFixedThreadPool(5);
    private static Executor dbWriteExecutor = Executors.newFixedThreadPool(10);

    public static synchronized Executor getSingleExecutorByKey(String key){
        Executor singleExecutor = singleExecutorMap.get(key);
        if(null == singleExecutor){
            singleExecutor = Executors.newSingleThreadExecutor();
            singleExecutorMap.put(key,singleExecutor);
        }
        return singleExecutor;
    }

    public static synchronized Executor getNormalMsgExecutor(){
        return normalMsgExecutor;
    }

    public static synchronized Executor getDbWriteExecutor(){
        return dbWriteExecutor;
    }
}
