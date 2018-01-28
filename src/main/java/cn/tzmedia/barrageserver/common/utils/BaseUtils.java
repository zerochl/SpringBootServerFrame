package cn.tzmedia.barrageserver.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/9/28.
 */
public class BaseUtils {
    public static String key = "wow!@#$%";

    public static <T> List<T> getRandomMaxList(List<T> showList,int maxSize,boolean clearOldList){
        if(null == showList){
            return null;
        }
        //超出最大条则随机获取
        if(showList.size() > maxSize){
            ArrayList<Integer> randomList = getRandomIndex(showList.size(),maxSize);
            ArrayList<T> newShowInfoList = new ArrayList<>();
            for(Integer randomIndex:randomList){
                newShowInfoList.add(showList.get(randomIndex));
            }
            if(clearOldList){
                showList.clear();
            }
            return newShowInfoList;
        }
        return showList;
    }

    private static Random random = new Random();

    private static ArrayList<Integer> getRandomIndex(int size,int count){
        ArrayList<Integer> randomList = new ArrayList<>();
        if(0 == size){
            return randomList;
        }
        int randomIndex = random.nextInt(size);
        for(int i = 0;i < count;i++){
            randomList.add((randomIndex + i) % size);
        }
        return randomList;
    }

    /**
     * 获取当前类的Fields以及父类的
     * @param obj
     * @return
     */
    public static Field[] getObjectAllFields(Object obj){
        if(null == obj){
            return null;
        }
        Field[] allFields = obj.getClass().getDeclaredFields();
        Class<?> classTemp = obj.getClass();
        //如果父类已经是Object类则不需要继续获取，如果父类和自己想同也不需要继续获取
        while(!classTemp.getSuperclass().equals(Object.class) && !classTemp.getSuperclass().equals(classTemp)){
            Field[] fields2 = classTemp.getSuperclass().getDeclaredFields();
            //重新开辟新的数组用以保存field
            Field[] newFields = new Field[allFields.length+fields2.length];
            //设置值
            System.arraycopy(allFields, 0, newFields, 0, allFields.length);
            System.arraycopy(fields2, 0, newFields, allFields.length, fields2.length);
            //执行循环替换
            allFields = newFields;
            classTemp = classTemp.getSuperclass();
        }
        return allFields;
    }

    public static boolean isStringInArray(String str, String[] array){
        for (String val:array){
            if(str.equals(val)){
                return true;
            }
        }
        return false;
    }

    public static String encode(String str){
        String enStr = "";
        try {
            enStr = DesUtil.encrypt(str, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return enStr;
    }

    public static String decode(String str) {
        String deStr = "";
        try {
            deStr = DesUtil.decrypt(str, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deStr;
    }
    public static void sleep(int secondTime){
        try {
            Thread.sleep(secondTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getRandomSmallSign() {
        int randInt = (int) (Math.random() * 100);
        if (randInt < 10) {
            randInt = randInt + 10;
        }
        return DateUtils.getTimeStampSmallMilli() + randInt;
    }

    public static long getRandomIntSign(){
        return Long.parseLong(getRandomSmallSign());
    }

    public static String getExceptionStr(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}
