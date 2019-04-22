package com.lining.ad.search.vo.feature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * className DistrictFeature
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/22 19:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictFeature {

    private List<ProviceAndCity> districts;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProviceAndCity {

        private String privince;
        private String city;
    }
}
