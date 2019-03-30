package com.lining.ad.service;

import com.lining.ad.Application;
import com.lining.ad.dao.AdPlanRepository;
import com.lining.ad.dao.AdUnitRepository;
import com.lining.ad.dao.CreativeRepository;
import com.lining.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.lining.ad.dao.unit_condition.AdUnitItRepository;
import com.lining.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.lining.ad.dao.unit_condition.CreativeUnitRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * className DumpDataService
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/30 17:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
    webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DumpDataService {

    @Autowired
    private AdPlanRepository planRepository;
    @Autowired
    private AdUnitRepository unitRepository;
    @Autowired
    private CreativeRepository creativeRepository;
    @Autowired
    private CreativeUnitRepository creativeUnitRepository;
    @Autowired
    private AdUnitDistrictRepository districtRepository;
    @Autowired
    private AdUnitItRepository itRepository;
    @Autowired
    private AdUnitKeywordRepository keywordRepository;


}
