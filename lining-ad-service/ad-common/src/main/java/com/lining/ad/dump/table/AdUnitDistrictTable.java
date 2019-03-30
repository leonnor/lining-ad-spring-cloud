package com.lining.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className AdUnitDistrictTable
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/30 16:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitDistrictTable {

    private Long unitId;
    private String province;
    private String city;
}
