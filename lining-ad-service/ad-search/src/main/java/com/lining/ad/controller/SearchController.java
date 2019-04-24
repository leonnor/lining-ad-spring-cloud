package com.lining.ad.controller;

import com.alibaba.fastjson.JSON;
import com.lining.ad.annotation.IgnoreResponseAdvice;
import com.lining.ad.client.SponsorClient;
import com.lining.ad.client.vo.AdPlan;
import com.lining.ad.client.vo.AdPlanGetRequest;
import com.lining.ad.search.ISearch;
import com.lining.ad.search.vo.SearchRequest;
import com.lining.ad.search.vo.SearchResponse;
import com.lining.ad.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * className SearchController
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/25 17:09
 */
@Slf4j
@RestController
public class SearchController {

    private final ISearch iSearch;
    private final RestTemplate restTemplate;
    private final SponsorClient sponsorClient;

    @Autowired
    public SearchController(ISearch iSearch, RestTemplate restTemplate, SponsorClient sponsorClient) {
        this.iSearch = iSearch;
        this.restTemplate = restTemplate;
        this.sponsorClient = sponsorClient;
    }

    @PostMapping("/fetchAds")
    public SearchResponse fetchAds(@RequestBody SearchRequest request) {

        log.info("ad-search: fetchAds -> {}", JSON.toJSONString(request));
        return iSearch.fetchAds(request);
    }

    @IgnoreResponseAdvice
    @PostMapping("/getAdPlans")
    public CommonResponse<List<AdPlan>> getAdPlans(@RequestBody AdPlanGetRequest request){
        log.info("ad-search: getAdPlans -> {}", JSON.toJSONString(request));
        return sponsorClient.getAdPlans(request);
    }

    @SuppressWarnings("all")
    @IgnoreResponseAdvice
    @PostMapping("/getAdPlansByRibbon")
    public CommonResponse<List<AdPlan>> getAdPlansByRibbon(@RequestBody AdPlanGetRequest request){

        log.info("ad-search: getAdPlanByRibbon -> {}", JSON.toJSONString(request));
        return restTemplate.postForEntity(
                "http://eureka-client-ad-sponsor/get/adPlan",
                request,
                CommonResponse.class
        ).getBody();
    }

}
