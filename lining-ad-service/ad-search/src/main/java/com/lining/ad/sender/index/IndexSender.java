package com.lining.ad.sender.index;

import com.alibaba.fastjson.JSON;
import com.lining.ad.dump.table.*;
import com.lining.ad.handler.AdLevelDataHandler;
import com.lining.ad.index.DataLevel;
import com.lining.ad.mysql.constant.Constant;
import com.lining.ad.mysql.dto.MysqlRowData;
import com.lining.ad.sender.ISender;
import com.lining.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className IndexSender
 * description 实现增量数据的投递，构建增量索引
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/18 13:56
 */
 @Slf4j
 @Component("indexSender")
public class IndexSender implements ISender {

    @Override
    public void sender(MysqlRowData rowData) {

        String level = rowData.getLevel();

        if (DataLevel.LEVEL2.getLevel().equals(level)){
            Level2RowData(rowData);
        } else if (DataLevel.LEVEL3.equals(level)){
            Level3RowData(rowData);
        } else if (DataLevel.LEVEL4.equals(level)){
            Level4RowData(rowData);
        } else {
            log.error("MysqlRowData ERROR: {}", JSON.toJSONString(rowData));
        }
    }

    /**
     * 第二层级增量数据投递
     * 包含广告计划表和创意表
     * 主要是把MysqlRowData转换为Table类
     * @param rowData
     */
    private void Level2RowData(MysqlRowData rowData){

        if (rowData.getTableName().equals(
                Constant.AD_PLAN_TABLE_INFO.TABLE_NAME)) {
            List<AdPlanTable> planTables = new ArrayList<>();

            for (Map<String, String> fieldValueMap : rowData.getFieldValueMap()){
                AdPlanTable adPlanTable = new AdPlanTable();
                fieldValueMap.forEach((k, v) -> {
                    switch (k) {
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_ID:
                            adPlanTable.setId(Long.valueOf(v));
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_USER_ID:
                            adPlanTable.setUserId(Long.valueOf(v));
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_PLAN_STATUS:
                            adPlanTable.setPlanStatus(Integer.valueOf(v));
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_START_DATE:
                            adPlanTable.setStartDate(
                                    CommonUtils.parseStringDate(v)
                            );
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_END_DATE:
                            adPlanTable.setEndDate(
                                    CommonUtils.parseStringDate(v)
                            );
                            break;
                         default:
                            break;
                    }
                });
                planTables.add(adPlanTable);
            }
            planTables.forEach(p ->
                    AdLevelDataHandler.handleLevel2(p, rowData.getOpType()));
        } else if (rowData.getTableName().equals(
                Constant.AD_CREATIVE_TABLE_INFO.TABLE_NAME)) {

            List<AdCreativeTable> creativeTables = new ArrayList<>();

            for (Map<String, String> fieldValueMap : rowData.getFieldValueMap()){
                AdCreativeTable creativeTable = new AdCreativeTable();
                fieldValueMap.forEach((k, v) -> {
                    switch (k){
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_ID:
                            creativeTable.setAdId(Long.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_TYPE:
                            creativeTable.setType(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_MATERIAL_TYPE:
                            creativeTable.setMaterialType(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_HEIGHT:
                            creativeTable.setHeight(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_WIDTH:
                            creativeTable.setWidth(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_AUDIT_STATUS:
                            creativeTable.setAuditStatus(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_URL:
                            creativeTable.setAdUrl(v);
                            break;
                            default:
                                break;
                    }
                });
                creativeTables.add(creativeTable);
            }
            creativeTables.forEach(c ->
                    AdLevelDataHandler.handleLevel2(c, rowData.getOpType()));
        }
    }

    /**
     * 第三层级增量数据投递
     * 包含AdUnit和CreativeUnit
     * @param rowData
     */
    private void Level3RowData(MysqlRowData rowData){

        if (rowData.getTableName().equals(
                Constant.AD_UNIT_TABLE_INFO.TABLE_NAME)){

            List<AdUnitTable> unitTables = new ArrayList<>();

            for (Map<String, String> fieldValueMap : rowData.getFieldValueMap()) {

                AdUnitTable unitTable = new AdUnitTable();

                fieldValueMap.forEach((k, v) -> {
                    switch (k) {
                        case Constant.AD_UNIT_TABLE_INFO.COLUMN_ID:
                            unitTable.setUnitId(Long.valueOf(v));
                            break;
                        case Constant.AD_UNIT_TABLE_INFO.COLUMN_PLAN_ID:
                            unitTable.setPlanId(Long.valueOf(v));
                            break;
                        case Constant.AD_UNIT_TABLE_INFO.COLUMN_POSITION_TYPE:
                            unitTable.setPositionType(Integer.valueOf(v));
                            break;
                        case Constant.AD_UNIT_TABLE_INFO.COLUMN_UNIT_STATUS:
                            unitTable.setUnitStatus(Integer.valueOf(v));
                            break;
                            default:
                                break;
                    }
                });
                unitTables.add(unitTable);
            }
            unitTables.forEach(u ->
                    AdLevelDataHandler.handleLevel3(u, rowData.getOpType()));
        } else if (rowData.getTableName().equals(
                Constant.AD_CREATIVE_UNIT_TABLE_INFO.TABLE_NAME)){

            List<AdCreativeUnitTable> creativeUnitTables = new ArrayList<>();

            for (Map<String, String> fieldValueMap : rowData.getFieldValueMap()){

                AdCreativeUnitTable adCreativeUnitTable = new AdCreativeUnitTable();

                fieldValueMap.forEach((k, v) -> {
                    switch (k) {
                        case Constant.AD_CREATIVE_UNIT_TABLE_INFO.COLUMN_CREATIVE_ID:
                            adCreativeUnitTable.setAdId(Long.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_UNIT_TABLE_INFO.COLUMN_UNIT_ID:
                            adCreativeUnitTable.setUnitId(Long.valueOf(v));
                            break;
                            default:
                                break;
                    }
                });
                creativeUnitTables.add(adCreativeUnitTable);
            }
            creativeUnitTables.forEach(c ->
                    AdLevelDataHandler.handleLevel3(c, rowData.getOpType()));
        }
    }

    /**
     * 第三层级增量数据投递
     * 包含UnitDistrict, UnitIt, UnitKeyword
     * @param rowData
     */
    private void Level4RowData(MysqlRowData rowData){

        switch (rowData.getTableName()) {

            case Constant.AD_UNIT_DISTRICT_TABLE_INFO.TABLE_NAME:
                List<AdUnitDistrictTable> districtTables = new ArrayList<>();

                for (Map<String, String> fieldValueMap : rowData.getFieldValueMap()){

                    AdUnitDistrictTable districtTable = new AdUnitDistrictTable();

                    fieldValueMap.forEach((k, v) -> {
                        switch (k) {
                            case Constant.AD_UNIT_DISTRICT_TABLE_INFO.COLUMN_UNIT_ID:
                                districtTable.setUnitId(Long.valueOf(v));
                                break;
                            case Constant.AD_UNIT_DISTRICT_TABLE_INFO.COLUMN_PROVINCE:
                                districtTable.setProvince(v);
                                break;
                            case Constant.AD_UNIT_DISTRICT_TABLE_INFO.COLUMN_CITY:
                                districtTable.setCity(v);
                                break;
                                default:
                                    break;
                        }
                    });
                    districtTables.add(districtTable);
                }
                districtTables.forEach(d ->
                        AdLevelDataHandler.handleLevel4(d, rowData.getOpType()));
                break;

            case Constant.AD_UNIT_IT_TABLE_INFO.TABLE_NAME:

                List<AdUnitItTable> itTables = new ArrayList<>();

                for (Map<String, String> fieldValueMap : rowData.getFieldValueMap()){

                    AdUnitItTable itTable = new AdUnitItTable();

                    fieldValueMap.forEach((k,v) -> {
                        switch (k) {
                            case Constant.AD_UNIT_IT_TABLE_INFO.COLUMN_UNIT_ID:
                                itTable.setUnitId(Long.valueOf(v));
                                break;
                            case Constant.AD_UNIT_IT_TABLE_INFO.COLUMN_IT_TAG:
                                itTable.setItTag(v);
                                break;
                                default:
                                    break;
                        }
                    });
                    itTables.add(itTable);
                }
                itTables.forEach(i ->
                        AdLevelDataHandler.handleLevel4(i, rowData.getOpType()));
                break;

            case Constant.AD_UNIT_KEYWORD_TABLE_INFO.TABLE_NAME:

                List<AdUnitKeywordTable> keywordTables = new ArrayList<>();

                for (Map<String, String> fieldValueMap : rowData.getFieldValueMap()){

                    AdUnitKeywordTable keywordTable = new AdUnitKeywordTable();

                    fieldValueMap.forEach((k, v) -> {
                        switch (k) {

                            case Constant.AD_UNIT_KEYWORD_TABLE_INFO.COLUMN_UNIT_ID:
                                keywordTable.setUnitId(Long.valueOf(v));
                                break;
                            case Constant.AD_UNIT_KEYWORD_TABLE_INFO.COLUMN_KEYWORD:
                                keywordTable.setKeyword(v);
                                break;
                                default:
                                    break;
                        }
                    });
                    keywordTables.add(keywordTable);
                }
                keywordTables.forEach(k ->
                        AdLevelDataHandler.handleLevel4(k, rowData.getOpType()));
                break;

                default:
                break;
        }
    }
}
