package com.lining.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * className AdPlanRequest
 * description 推广计划的请求参数
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/15 19:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanRequest {

    private Long id;
    private Long userId;
    private String planName;
    private String startDate;
    private String endDate;

    //创建时
    public boolean createValidate(){

        return userId != null
                && !StringUtils.isEmpty(planName)
                && !StringUtils.isEmpty(startDate)
                && !StringUtils.isEmpty(endDate);
    }

    //更新时
    public boolean updateValidate(){

        return id != null && userId != null;
    }

    //删除时
    public boolean deleteValidate(){

        return id != null && userId != null;
    }
}
