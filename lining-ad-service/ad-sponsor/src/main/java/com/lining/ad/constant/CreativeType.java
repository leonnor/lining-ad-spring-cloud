package com.lining.ad.constant;

import lombok.Getter;

/**
 * className CreativeType
 * description 创意类型常量
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/13 20:06
 */
@Getter
public enum CreativeType {

    IMAGE(1, "图片"),
    VIDEO(2, "视频"),
    TEXT(3, "文本");

    private int type;
    private String desc;

    CreativeType(int type, String desc){
        this.type = type;
        this.desc = desc;
    }
}
