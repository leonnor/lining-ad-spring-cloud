package com.lining.ad.untils;

import java.util.Map;
import java.util.function.Supplier;

/**
 * className CommonUtils
 * description 用于当用key去获取值的时候不存在的情况下返回set
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/27 18:52
 */
public class CommonUtils {

    public static <K, V> V getorCreate(K key, Map<K, V> map, Supplier<V> factory){
        return map.computeIfAbsent(key, k -> factory.get());
    }

}
