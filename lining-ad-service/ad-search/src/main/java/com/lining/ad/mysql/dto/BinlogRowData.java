package com.lining.ad.mysql.dto;

import com.github.shyiko.mysql.binlog.event.EventType;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * className BinlogRowData
 * description TODO
 * 把binlog的日志数据转换成检索系统中定义的Java对象
 * @author ln
 * @version 1.0
 * @date 2019/4/8 20:44
 */
@Data
public class BinlogRowData {

    private TableTemplate table;

    private EventType eventType;

    private List<Map<String, String>> after;

    private List<Map<String, String>> before;
}
