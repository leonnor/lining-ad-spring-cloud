package com.lining.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * className AdUnitDistrictRequest
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/16 20:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitDistrictRequest {

    private List<UnitDistrict> unitDistricts;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    /** 更标准的做法应该用地域字典来存储地域信息*/
    public static class UnitDistrict {

        private Long unitId;
        private String province;
        private String city;
    }
}
