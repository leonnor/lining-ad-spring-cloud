package com.lining.ad.controller;

import com.alibaba.fastjson.JSON;
import com.lining.ad.entity.AdPlan;
import com.lining.ad.exception.AdException;
import com.lining.ad.service.IAdPlanService;
import com.lining.ad.vo.AdPlanGetRequest;
import com.lining.ad.vo.AdPlanRequest;
import com.lining.ad.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * className AdPlanOPController
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/21 19:01
 */
@Slf4j
@RestController
public class AdPlanOPController {

    private final IAdPlanService adPlanService;

    @Autowired
    public AdPlanOPController(IAdPlanService adPlanService) {
        this.adPlanService = adPlanService;
    }

    @PostMapping("/create/adPlan")
    public AdPlanResponse createAdPlan(@RequestBody AdPlanRequest request) throws AdException{

        log.info("ad-sponsor: createAdPlan -> {}", JSON.toJSONString(request));
        return adPlanService.createAdPlan(request);
    }

    @PostMapping("/get/adPlan")
    public List<AdPlan> getAdPlanByIds(@RequestBody AdPlanGetRequest request) throws AdException{

        log.info("ad-sponsor: getAdPlanByIds ->{}", JSON.toJSONString(request));
        return adPlanService.getAdPlanByIds(request);
    }

    @PutMapping("/update/adPlan")
    public AdPlanResponse updateAdPlan(@RequestBody AdPlanRequest request) throws AdException{
        log.info("ad-sponsor: updateAdPlan ->{}", JSON.toJSONString(request));
        return adPlanService.updateAdPlan(request);
    }

    @DeleteMapping("/delete/adPlan")
    public void deleteAdPlan(@RequestBody AdPlanRequest request) throws AdException{
        log.info("ad-sponsor: deleteAdPlan ->{}", JSON.toJSONString(request));
        adPlanService.deleteAdPlan(request);
    }
}
