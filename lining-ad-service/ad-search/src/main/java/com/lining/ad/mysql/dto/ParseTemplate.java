package com.lining.ad.mysql.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * className ParseTemplate
 * description 模版文件的对象表示
 * 解析模板
 * @author ln
 * @version 1.0
 * @date 2019/4/6 20:43
 */
@Data
public class ParseTemplate {

    private String database;

    private Map<String, TableTemplate> tableTemplateMap = new HashMap<>();

    public static ParseTemplate parse(Template _template){


    }
}
