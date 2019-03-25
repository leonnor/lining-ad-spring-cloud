package com.lining.ad.client;

import com.lining.ad.client.vo.AdPlan;
import com.lining.ad.client.vo.AdPlanGetRequest;
import com.lining.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * className SponsorClientHystrix
 * description 断路器
 * 调用AdSponsor过程中发生错误就会做服务降级，调用这里的方法，防止雪崩。
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/25 18:36
 */
@Component
public class SponsorClientHystrix implements SponsorClient {

    @Override
    public CommonResponse<List<AdPlan>> getAdPlans(AdPlanGetRequest request) {
        return new CommonResponse<>(-1, "eureka-client-ad-sponsor error");
    }
}
