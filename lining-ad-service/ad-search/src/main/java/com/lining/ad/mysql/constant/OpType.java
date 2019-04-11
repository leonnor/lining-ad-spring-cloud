package com.lining.ad.mysql.constant;

import com.github.shyiko.mysql.binlog.event.EventType;

/**
 * className OpType
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/1 16:36
 */
public enum OpType {

    ADD,
    UPDATE,
    DELETE,
    OTHER;

    /**
     * 将eventType转换为OpType
     * @param eventType
     * @return
     */
    public static OpType to(EventType eventType){

        switch (eventType) {
            case EXT_WRITE_ROWS:
                return ADD;
            case EXT_UPDATE_ROWS:
                return UPDATE;
            case EXT_DELETE_ROWS:
                return DELETE;
            default:
                return OTHER;
        }
    }
}
