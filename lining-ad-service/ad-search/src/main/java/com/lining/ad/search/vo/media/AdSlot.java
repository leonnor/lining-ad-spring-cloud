package com.lining.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * className AdSlot
 * description 广告位信息
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/20 19:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdSlot {

    /** 广告位编码*/
    private String adSlotCode;

    /** 流量类型*/
    private Integer positionType;

    /** 宽和高*/
    private Integer width;

    private Integer height;

    /** 广告物料类型： 图片、视频*/
    private List<Integer> type;

    /** 最低出价*/
    private Integer minCpm;
}
