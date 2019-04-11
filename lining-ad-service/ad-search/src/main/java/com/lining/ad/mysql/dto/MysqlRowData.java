package com.lining.ad.mysql.dto;

import com.lining.ad.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className MysqlRowData
 * description 投递的对象
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/11 13:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MysqlRowData {

    private String tableName;

    private String level;

    private OpType opType;

    private List<Map<String, String>> fieldValueMap = new ArrayList<>();
}
