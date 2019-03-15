package com.lining.ad.utils;


import com.lining.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * className CommonUtils
 * description
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/15 18:15
 */
public class CommonUtils {

    //设置能接收的日期格式
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"
    };

    /**
     * 获取字符串的md5
     * @param value
     * @return
     */
    public static String md5(String value){

        return DigestUtils.md5Hex(value).toUpperCase();
    }

    /**
     * 将String类型的日期转换成Date类型
     * @param dateString
     * @return
     * @throws AdException
     */
    public static Date parseStringDate(String dateString) throws AdException {
        try{
            return DateUtils.parseDate(dateString, parsePatterns);
        } catch (Exception e){
            throw new AdException(e.getMessage());
        }
    }
}
