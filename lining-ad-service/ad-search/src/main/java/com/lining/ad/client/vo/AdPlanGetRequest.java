package com.lining.ad.client.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * className AdPlanGetRequest
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/25 16:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanGetRequest {

    private Long userId;
    private List<Long> ids;
}
