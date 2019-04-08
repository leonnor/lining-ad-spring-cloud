package com.lining.ad.mysql.listener;

import com.lining.ad.mysql.dto.BinlogRowData;

/**
 * className Ilistener
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/8 20:49
 */
public interface Ilistener {

    void register();

    void onEvent(BinlogRowData eventData);
}
