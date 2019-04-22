package com.lining.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className Geo
 * description 地理位置信息
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/20 19:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geo {

    /** 维度*/
    private Float latitude;
    /** 经度*/
    private Float longitude;

    private String city;
    private String province;
}
