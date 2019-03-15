package com.lining.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * className AdPlanGetRequest
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/15 19:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanGetRequest {

    private Long userId;

    //用于批量获取多个推广计划
    private List<Long> ids;

    public boolean validate(){

        return userId != null && !CollectionUtils.isEmpty(ids);
    }

}
