package com.lining.ad.constant;

import lombok.Getter;

/**
 * className CreativeMaterialType
 * description 物料类型常量
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/13 20:09
 */
@Getter
public enum CreativeMaterialType {

    JPG(1, "jpg"),
    BMP(2, "bmp"),

    MP4(3, "mp4"),
    AVI(4, "avi"),

    TXT(5, "txt");

    private int type;
    private String desc;

    CreativeMaterialType(int type, String desc){
        this.type = type;
        this.desc = desc;
    }
}
