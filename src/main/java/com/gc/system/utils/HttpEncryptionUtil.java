package com.gc.system.utils;

import com.gc.common.utils.StringUtils;

import java.util.Arrays;

/**
 * 作者  zhuangwentao
 * 日期  2016-02-26.
 */
public class HttpEncryptionUtil {

    public static final String SECRET = "e10adc3949ba59abbe56e057f20f883e";
    public static final String APP_SECRET = "abcde";

    public static String getEncryptionMes(String shopId,String dateStamp){

        if(StringUtils.isBlank(shopId)||StringUtils.isBlank(dateStamp)){
            return null;
        }
        String[] arr = new String[]{shopId,dateStamp,SECRET};
        //排序
        Arrays.sort(arr);

        //生成字符串
        StringBuffer content = new StringBuffer();
        for(int i=0;i<arr.length;i++){
            content.append(arr[i]);
        }

        return MD5Util.md5Hex(content.toString());
    }

    public static String getAppEncryptionMes(String dateStamp){

        if(StringUtils.isBlank(dateStamp)){
            return null;
        }
        //生成字符串
        StringBuffer content = new StringBuffer();
        content.append(dateStamp);
        content.append(APP_SECRET);
        return MD5Util.md5Hex(content.toString());
    }
}
