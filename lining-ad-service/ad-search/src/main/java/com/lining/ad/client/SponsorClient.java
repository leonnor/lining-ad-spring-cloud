package com.lining.ad.client;

import com.lining.ad.client.vo.AdPlan;
import com.lining.ad.client.vo.AdPlanGetRequest;
import com.lining.ad.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * className SponsorClient
 * description 基于Feign实现微服务调用
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/25 18:26
 */
@FeignClient(value = "eureka-client-ad-sponsor", fallback = SponsorClientHystrix.class)
public interface SponsorClient {

    @RequestMapping(value = "/ad-sponsor/get/adPlan", method = RequestMethod.POST)
    CommonResponse<List<AdPlan>> getAdPlans(@RequestBody AdPlanGetRequest request);
}
