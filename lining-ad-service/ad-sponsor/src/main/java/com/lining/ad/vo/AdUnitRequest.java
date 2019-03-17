package com.lining.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * className AdUnitRequest
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/16 10:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitRequest {

    private Long planId;
    private String unitName;
    private Integer positionType;
    private Long budget;

    public boolean createValidate(){

        return null != planId && !StringUtils.isEmpty(unitName) && null != positionType
                && null != budget && planId >= 0 && positionType > 0 && budget > 0;
    }
}
