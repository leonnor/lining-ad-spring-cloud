package com.lining.ad.index.creativeunit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className CreativeUnitObject
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/28 14:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreativeUnitObject {

    /** 创意对象*/
    private Long adId;
    private Long unitId;
    /**
     * key为adId-unitId， value为CreativeUnitObject
     */
}
