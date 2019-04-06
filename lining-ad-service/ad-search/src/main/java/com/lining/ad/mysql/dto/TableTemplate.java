package com.lining.ad.mysql.dto;

import com.lining.ad.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className TableTemplate
 * description 操作时方便读取表信息
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/6 20:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableTemplate {

    private String tableName;
    private String level;

    private Map<OpType, List<String>> opTypeFieldSetMap = new HashMap<>();

    /**
     * 字段索引 -> 字段名 的映射
     */
    private Map<Integer, String> posMap = new HashMap<>();
}
