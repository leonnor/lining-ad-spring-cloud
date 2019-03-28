package com.lining.ad.index.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className UnitDistrictObject
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/28 13:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitDistrictObject {

    private Long unitId;
    private String province;
    private String city;
    /**
     * <String, Set<Long>>
     * 这里的String是province-city
     */
}
