package com.lining.ad.index;

import lombok.Getter;

/**
 * className CommonStauts
 * description 标记推广单元和推广计划的状态
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/23 19:06
 */
@Getter
public enum CommonStauts {

    VALID(1, "有效状态"),
    INVALID(0, "无效状态");

    private Integer status;
    private String desc;

    CommonStauts(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
