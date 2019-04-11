package com.lining.ad.mysql.listener;

import com.github.shyiko.mysql.binlog.event.EventType;
import com.lining.ad.mysql.constant.Constant;
import com.lining.ad.mysql.constant.OpType;
import com.lining.ad.mysql.dto.BinlogRowData;
import com.lining.ad.mysql.dto.MysqlRowData;
import com.lining.ad.mysql.dto.TableTemplate;
import com.lining.ad.sender.ISender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className IncrementListener
 * description 增量数据的处理器
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/11 13:21
 */
@Slf4j
@Component
public class IncrementListener implements Ilistener{

    @Resource(name = "")
    private ISender sender;

    private final AggregationListener aggregationListener;

    @Autowired
    public IncrementListener(AggregationListener aggregationListener) {
        this.aggregationListener = aggregationListener;
    }

    /**
     * 实现表注册
     */
    @Override
    @PostConstruct
    public void register() {

        log.info("IncrementListener register db and table info");
        Constant.table2Db.forEach((k, v) -> aggregationListener.register(v, k, this));
    }

    /**
     * 将BinlogRowData转换成MysqlRowData
     * @param eventData
     */
    @Override
    public void onEvent(BinlogRowData eventData) {
        TableTemplate table = eventData.getTable();
        EventType eventType = eventData.getEventType();

        /** 包装成最后需要投递的数据*/
        MysqlRowData rowData = new MysqlRowData();

        rowData.setTableName(table.getTableName());
        rowData.setLevel(eventData.getTable().getLevel());
        OpType opType = OpType.to(eventType);
        rowData.setOpType(opType);

        /** 取出模板中该操作对应的字段列表*/
        List<String> fieldList = table.getOpTypeFieldSetMap().get(opType);
        if (null == fieldList){
            log.warn("{} not support for {}", opType, table.getTableName());
            return;
        }

        for (Map<String, String> afterMap : eventData.getAfter()){

            Map<String, String> _afterMap = new HashMap<>();

            for (Map.Entry<String, String> entry : afterMap.entrySet()){
                String colName = entry.getKey();
                String colValue = entry.getValue();

                _afterMap.put(colName, colValue);
            }

            rowData.getFieldValueMap().add(_afterMap);
        }
        sender.sender(rowData);
    }
}
