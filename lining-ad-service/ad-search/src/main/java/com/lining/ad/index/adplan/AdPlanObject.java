package com.lining.ad.index.adplan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * className AdPlanObject
 * description 推广计划的索引类
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/25 20:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanObject {

    private Long planId;
    private Long userId;
    private Integer planStatus;
    private Date startDate;
    private Date endDate;

    public void update(AdPlanObject newObject) {

        if (null != newObject.getPlanId()) {
            this.planId = newObject.getPlanId();
        }
        if (null != newObject.getUserId()) {
            this.userId = newObject.getUserId();
        }
        if (null != newObject.getPlanStatus()) {
            this.planStatus = newObject.getPlanStatus();
        }
        if (null != newObject.getStartDate()) {
            this.startDate = newObject.getStartDate();
        }
        if (null != newObject.getEndDate()) {
            this.endDate = newObject.getEndDate();
        }
    }
}
