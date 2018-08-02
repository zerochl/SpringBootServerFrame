package com.zero.barrageserver.common.utils;

import com.zero.barrageserver.common.manager.ExecutorManager;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Log4j2
public class MD5Util {

    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(MD5Util.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
            nsaex.printStackTrace();
            log.error("MD5Util:" + BaseUtils.getExceptionStr(nsaex));
        }
    }

    public static void main(String[] args) throws IOException {
//        long begin = System.currentTimeMillis();
//
//        File big = new File("I:/123.gif");
//
//        String md5 = getFileMD5String(big);
//
//        long end = System.currentTimeMillis();
//        System.out.println("md5:" + md5.toUpperCase() + " time:" + ((end - begin) / 1000) + "s");
        String json = "{\"action\":\"update\",\"retryCount\":0,\"tableName\":\"barrage.device\",\"type\":\"BARRAGE\",\"weight\":1,\"writeValue\":\"{\\\"ip\\\":\\\"192.168.0.94\\\",\\\"shopId\\\":\\\"55c43920bdf67cc961e53992\\\",\\\"sn\\\":\\\"12361M580604377\\\",\\\"version\\\":\\\"2.4.22\\\"}\"}";
        String realMD5 = getMD5String(json);
        System.out.println("real md5:" + realMD5);
//        String realMD52 = getMD5String(json);
//        System.out.println("real md5:" + realMD52);
        for(int i = 0;i < 1000;i++){
            Observable.create((ObservableOnSubscribe<Integer>) observableEmitter -> {
//                log.info("on subscribe:" + ";thread:" + Thread.currentThread().getId());
                //执行写入操作
                String md5 = getMD5String(json);
                if(!realMD5.equals(md5)){
                    System.out.println("不匹配:" + md5);
                }
            })
                    //此存储操作不需要考虑先后顺序，快速消费即可
                    .subscribeOn(Schedulers.from(ExecutorManager.getDbWriteExecutor()))
                    .subscribe();

        }
    }

    public static String getFileMD5String(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        messagedigest.update(byteBuffer);
        String md5 = bufferToHex(messagedigest.digest());
        if (!StringUtil.isEmpty(md5)) {
            md5 = md5.toUpperCase();
        }
        in.close();
        ch.close();
        return md5;
    }

//    public static String getMD5String(String s) {
//        return getMD5String(s.getBytes());
//    }
//
//    public static String getMD5String(byte[] bytes) {
//        messagedigest.update(bytes);
//        return bufferToHex(messagedigest.digest());
//    }

    public static String getMD5String(String pwd) {
        //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
        byte[] btInput = pwd.getBytes();
        return getMD5String(btInput);
    }

    public static String getMD5String(byte[] btInput) {
        //用于加密的字符
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {

            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }

            //返回经过加密后的字符串
            return new String(str);

        } catch (Exception e) {
            return null;
        }
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }


    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }
}
