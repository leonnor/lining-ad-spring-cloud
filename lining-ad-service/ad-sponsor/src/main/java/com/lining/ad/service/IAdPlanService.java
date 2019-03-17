package com.lining.ad.service;

import com.lining.ad.entity.AdPlan;
import com.lining.ad.exception.AdException;
import com.lining.ad.vo.AdPlanGetRequest;
import com.lining.ad.vo.AdPlanResponse;
import com.lining.ad.vo.AdPlanRequest;

import java.util.List;

/**
 * className IAdPlanService
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/15 19:34
 */
public interface IAdPlanService {

    /**
     * 创建推广计划
     * @param request
     * @return
     * @throws AdException
     */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 批量获取推广计划
     * @param request
     * @return
     * @throws AdException
     */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    /**
     * 更新推广计划
     * @param request
     * @return
     * @throws AdException
     */
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 删除推广计划
     * @param request
     * @throws AdException
     */
    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
