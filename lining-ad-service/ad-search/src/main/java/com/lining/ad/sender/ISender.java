package com.lining.ad.sender;

import com.lining.ad.mysql.dto.MysqlRowData;

/**
 * className ISender
 * description 投递增量数据的方法接口
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/11 13:19
 */
public interface ISender {

    void sender(MysqlRowData rowData);
}
