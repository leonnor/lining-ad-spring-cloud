package com.lining.ad.service;

import com.alibaba.fastjson.JSON;
import com.lining.ad.Application;
import com.lining.ad.constant.CommonStatus;
import com.lining.ad.dao.AdPlanRepository;
import com.lining.ad.dao.AdUnitRepository;
import com.lining.ad.dao.CreativeRepository;
import com.lining.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.lining.ad.dao.unit_condition.AdUnitItRepository;
import com.lining.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.lining.ad.dao.unit_condition.CreativeUnitRepository;
import com.lining.ad.dump.table.AdPlanTable;
import com.lining.ad.dump.table.AdUnitTable;
import com.lining.ad.entity.AdPlan;
import com.lining.ad.entity.AdUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * className DumpDataService
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/30 17:09
 */
@Slf4j
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

    /**
     * 将当前数据库中有效的推广计划导出到文件
     * @param fileName
     */
     private void dumpAdPlanTable(String fileName){

         List<AdPlan> adPlans = planRepository.findAllByPlanStatus(
                 CommonStatus.VALID.getStatus()
         );
         if (CollectionUtils.isEmpty(adPlans)){
             return;
         }

         List<AdPlanTable> planTables = new ArrayList<>();
         adPlans.forEach(p -> planTables.add(new AdPlanTable(
                 p.getId(),
                 p.getUserId(),
                 p.getPlanStatus(),
                 p.getStartDate(),
                 p.getEndDate()
         )));

         Path path = Paths.get(fileName);
         try (BufferedWriter writer = Files.newBufferedWriter(path)){
             for (AdPlanTable planTable : planTables){
                 writer.write(JSON.toJSONString(planTable));
                 writer.newLine();
             }
         } catch (IOException e){
             log.error("dumpAdPlanTable error");
         }
     }

    /**
     * 导出AdUnit的方法
     * @param fileName
     */
     private void dumpAdUnitTable(String fileName){

        List<AdUnit> adUnits = unitRepository.findAllByUnitStatus(
                CommonStatus.VALID.getStatus()
        );
        if (CollectionUtils.isEmpty(adUnits)){
            return;
        }

        List<AdUnitTable> unitTables = new ArrayList<>();
        adUnits.forEach(u ->unitTables.add(new AdUnitTable(
                u.getId(),
                u.getUnitStatus(),
                u.getPositionType(),
                u.getPlanId()
                )
        ));

     }
}
