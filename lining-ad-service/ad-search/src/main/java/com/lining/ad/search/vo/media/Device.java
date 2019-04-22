package com.lining.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className Device
 * description 设备信息
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/22 19:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    // 设备 id
    private String deviceCode;

    // mac
    private String mac;

    // ip
    private String ip;

    // 机型编码
    private String model;

    // 分辨率尺寸
    private String displaySize;

    // 屏幕尺寸
    private String screenSize;

    // 设备序列号
    private String serialName;
}
