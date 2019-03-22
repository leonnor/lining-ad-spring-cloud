package com.lining.ad.controller;

import com.alibaba.fastjson.JSON;
import com.lining.ad.entity.Creative;
import com.lining.ad.exception.AdException;
import com.lining.ad.service.ICreativeService;
import com.lining.ad.vo.CreativeRequest;
import com.lining.ad.vo.CreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * className CreativeOPController
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/22 18:51
 */
@Slf4j
@RestController
public class CreativeOPController {

    private final ICreativeService creativeService;

    @Autowired
    public CreativeOPController(ICreativeService creativeService) {
        this.creativeService = creativeService;
    }

    public CreativeResponse createCreative(@RequestBody CreativeRequest request) throws AdException {
        log.info("ad-sponsor: createCreative -> {}", JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }
}
