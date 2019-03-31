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
import com.lining.ad.dump.DConstant;
import com.lining.ad.dump.table.*;
import com.lining.ad.entity.AdPlan;
import com.lining.ad.entity.AdUnit;
import com.lining.ad.entity.Creative;
import com.lining.ad.entity.unit_condition.AdUnitDistrict;
import com.lining.ad.entity.unit_condition.AdUnitIt;
import com.lining.ad.entity.unit_condition.AdUnitKeyword;
import com.lining.ad.entity.unit_condition.CreativeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
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


    @Test
    public void dumpAdTableData() {

        dumpAdPlanTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR,
                        DConstant.AD_PLAN)
        );
        dumpAdUnitTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT)
        );
        dumpAdCreativeTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR,
                        DConstant.AD_CREATIVE)
        );
        dumpAdCreativeUnitTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR,
                        DConstant.AD_CREATIVE_UNIT)
        );
        dumpAdUnitDistrictTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_DISTRICT)
        );
        dumpAdUnitItTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_IT)
        );
        dumpAdUnitKeywordTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_KEYWORD)
        );
    }


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

        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitTable unitTable : unitTables){
                writer.write(JSON.toJSONString(unitTable));
                writer.newLine();
            }
        } catch (IOException e){
            log.error("dumpAdUnitTable error");
        }
     }

     private void dumpAdCreativeTable(String fileName){

         List<Creative> creatives = creativeRepository.findAll();
         if (CollectionUtils.isEmpty(creatives)){
             return;
         }

         List<AdCreativeTable> creativeTables = new ArrayList<>();
         creatives.forEach(c -> creativeTables.add(new AdCreativeTable(
                 c.getId(),
                 c.getName(),
                 c.getType(),
                 c.getMaterialType(),
                 c.getHeight(),
                 c.getWidth(),
                 c.getAuditStatus(),
                 c.getUrl()
         )));

         Path path = Paths.get(fileName);
         try(BufferedWriter writer = Files.newBufferedWriter(path)){
             for (AdCreativeTable creativeTable : creativeTables){
                 writer.write(JSON.toJSONString(creativeTable));
                 writer.newLine();
             }
         } catch (IOException e){
             log.error("dumpAdCreative error");
         }
     }

     private void dumpAdCreativeUnitTable(String fileName){

         List<CreativeUnit> creativeUnits = creativeUnitRepository.findAll();
         if (CollectionUtils.isEmpty(creativeUnits)){
             return;
         }

         List<AdCreativeUnitTable> creativeUnitTables = new ArrayList<>();
         creativeUnits.forEach(c -> creativeUnitTables.add(new AdCreativeUnitTable(
                 c.getId(),
                 c.getUnitId()
         )));

         Path path = Paths.get(fileName);
         try(BufferedWriter writer = Files.newBufferedWriter(path)){
             for (AdCreativeUnitTable creativeUnitTable : creativeUnitTables){
                 writer.write(JSON.toJSONString(creativeUnitTable));
                 writer.newLine();
             }
         } catch (IOException e){
             log.error("dumpAdCreativeUnit error");
         }
     }



    private void dumpAdUnitDistrictTable(String fileName) {

        List<AdUnitDistrict> unitDistricts = districtRepository.findAll();
        if (CollectionUtils.isEmpty(unitDistricts)) {
            return;
        }

        List<AdUnitDistrictTable> unitDistrictTables = new ArrayList<>();
        unitDistricts.forEach(d -> unitDistrictTables.add(
                new AdUnitDistrictTable(
                        d.getUnitId(),
                        d.getProvince(),
                        d.getCity()
                )
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitDistrictTable unitDistrictTable : unitDistrictTables) {
                writer.write(JSON.toJSONString(unitDistrictTable));
                writer.newLine();
            }
        } catch (IOException ex) {
            log.error("dumpAdUnitDistrictTable error");
        }
    }

    private void dumpAdUnitItTable(String fileName) {

        List<AdUnitIt> unitIts = itRepository.findAll();
        if (CollectionUtils.isEmpty(unitIts)) {
            return;
        }

        List<AdUnitItTable> unitItTables = new ArrayList<>();
        unitIts.forEach(i -> unitItTables.add(
                new AdUnitItTable(
                        i.getUnitId(),
                        i.getItTag()
                )
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitItTable unitItTable : unitItTables) {
                writer.write(JSON.toJSONString(unitItTable));
                writer.newLine();
            }
        } catch (IOException ex) {
            log.error("dumpAdUnitItTable error");
        }
    }

    private void dumpAdUnitKeywordTable(String fileName) {

        List<AdUnitKeyword> unitKeywords = keywordRepository.findAll();
        if (CollectionUtils.isEmpty(unitKeywords)) {
            return;
        }

        List<AdUnitKeywordTable> unitKeywordTables = new ArrayList<>();
        unitKeywords.forEach(k -> unitKeywordTables.add(
                new AdUnitKeywordTable(
                        k.getUnitId(),
                        k.getKeyword()
                )
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitKeywordTable unitKeywordTable : unitKeywordTables) {
                writer.write(JSON.toJSONString(unitKeywordTable));
                writer.newLine();
            }
        } catch (IOException ex) {
            log.error("dumpAdUnitItTable error");
        }
    }
}
