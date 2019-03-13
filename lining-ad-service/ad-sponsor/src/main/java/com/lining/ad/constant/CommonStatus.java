package com.lining.ad.constant;

import lombok.Getter;
import org.omg.CORBA.INVALID_ACTIVITY;

/**
 * className CommonStatus
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/13 19:05
 */
@Getter
public enum CommonStatus {

    VALID(1, "有效状态"),
    INVALID(0, "无效状态");

    private Integer status;
    private String desc;

    CommonStatus(Integer status, String desc){
        this.status = status;
        this.desc = desc;
    }
}
