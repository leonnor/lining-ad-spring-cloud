package com.lining.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className AdPlanReponse
 * description 推广计划的响应参数
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/15 19:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanReponse {

    private Long id;
    private String planName;
}
