package com.lining.ad.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

/**
 * className CommonUtils
 * description 通用方法
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/27 18:52
 */
@Slf4j
public class CommonUtils {

    /**
     * 用于当用key去获取值的时候不存在的情况下返回set
     * @param key
     * @param map
     * @param factory
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> V getorCreate(K key, Map<K, V> map, Supplier<V> factory){
        return map.computeIfAbsent(key, k -> factory.get());
    }

    /**
     * 连接字符串方法，中间用-分割
     * @param args
     * @return
     */
    public static String stringConcat(String... args){

        StringBuilder result = new StringBuilder();
        for (String arg : args){
            result.append(arg);
            result.append("-");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    /**
     * 实现对binlog监听到的日期格式解析
     * 格式：Tue Jan 01 08:00:00 CST 2019
     * @param dateString
     * @return
     */
    public static Date parseStringDate(String dateString){

        try {

            DateFormat dateFormat = new SimpleDateFormat(
                    "EEE MMM dd HH:mm:ss zzz yyyy",
                    Locale.US
            );
            return DateUtils.addHours(
                    dateFormat.parse(dateString),
                    -8
            );
        } catch (ParseException ex){
            log.error("parseStringDate error: {}", dateString);
            return null;
        }
    }
}
