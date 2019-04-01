package com.lining.ad.untils;

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
}
