package com.lining.ad.handler;

import com.alibaba.fastjson.JSON;
import com.lining.ad.dump.table.*;
import com.lining.ad.index.DataTable;
import com.lining.ad.index.IndexAware;
import com.lining.ad.index.adplan.AdPlanIndex;
import com.lining.ad.index.adplan.AdPlanObject;
import com.lining.ad.index.adunit.AdUnitIndex;
import com.lining.ad.index.adunit.AdUnitObject;
import com.lining.ad.index.creative.CreativeIndex;
import com.lining.ad.index.creative.CreativeObject;
import com.lining.ad.index.creativeunit.CreativeUnitIndex;
import com.lining.ad.index.creativeunit.CreativeUnitObject;
import com.lining.ad.index.district.UnitDistrictIndex;
import com.lining.ad.index.interest.UnitItIndex;
import com.lining.ad.index.keyword.UnitKeywordIndex;
import com.lining.ad.mysql.constant.OpType;
import com.lining.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * className AdLevelDataHandler
 * description 索引操作实现
 * 这里需要关注两个问题：
 * 1.索引之间存在着层级的划分，也就是依赖关系的划分
 * 2.加载全量索引其实是增量索引“添加”的一种特殊实现
 * @author ln
 * @version 1.0
 * @date 2019/4/1 16:06
 */
@Slf4j
public class AdLevelDataHandler {

    /**
     * 第二层级的索引
     * @param planTable
     * @param type
     */
    public static void handleLevel2(AdPlanTable planTable, OpType type){

        AdPlanObject planObject = new AdPlanObject(
                planTable.getId(),
                planTable.getUserId(),
                planTable.getPlanStatus(),
                planTable.getStartDate(),
                planTable.getEndDate()
        );
        handleBinlogEvent(DataTable.of(AdPlanIndex.class),
                planObject.getPlanId(),
                planObject,
                type
                );
    }

    public static void handleLevel2(AdCreativeTable creativeTable, OpType type){

        CreativeObject creativeObject = new CreativeObject(
                creativeTable.getAdId(),
                creativeTable.getName(),
                creativeTable.getType(),
                creativeTable.getMaterialType(),
                creativeTable.getHeight(),
                creativeTable.getWidth(),
                creativeTable.getAuditStatus(),
                creativeTable.getAdUrl()
        );
        handleBinlogEvent(
                DataTable.of(CreativeIndex.class),
                creativeObject.getAdId(),
                creativeObject,
                type
        );
    }

    /**
     * 第三层级索引
     * @param unitTable
     * @param type
     */
    public static void handleLevel3(AdUnitTable unitTable, OpType type){

        AdPlanObject adPlanObject = DataTable.of(AdPlanIndex.class).get(unitTable.getPlanId());
        if (null == adPlanObject){
            log.error("handLevel3 found AdPlanObject error: {}", unitTable.getPlanId());
            return;
        }

        AdUnitObject unitObject = new AdUnitObject(
                unitTable.getUnitId(),
                unitTable.getUnitStatus(),
                unitTable.getPositionType(),
                unitTable.getPlanId(),
                adPlanObject
        );
    }

    public static void handleLevel3(AdCreativeUnitTable creativeUnitTable, OpType type){

        /** 这个索引对象不支持更新，所以要先判断操作类型是否是update*/
        if (type == OpType.UPDATE){
            log.error("CreativeUnitIndex not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class)
                .get(creativeUnitTable.getUnitId());
        CreativeObject creativeObject = DataTable.of(CreativeIndex.class)
                .get(creativeUnitTable.getAdId());
        if (null == unitObject || null == creativeObject){
            log.error("AdCreativeUnitTable index error: {}",
                    JSON.toJSONString(creativeUnitTable));
            return;
        }

        CreativeUnitObject creativeUnitObject = new CreativeUnitObject(
                creativeUnitTable.getAdId(),
                creativeUnitTable.getUnitId()
        );
        handleBinlogEvent(
                DataTable.of(CreativeUnitIndex.class),
                CommonUtils.stringConcat(
                        creativeUnitObject.getAdId().toString(),
                        creativeUnitObject.getUnitId().toString()
                ),
                creativeUnitObject,
                type
        );
    }

    /**
     * 第四层级索引
     * @param unitDistrictTable
     * @param type
     */
    public static void handleLevel4(AdUnitDistrictTable unitDistrictTable, OpType type){

        if (type == OpType.UPDATE){
            log.error("district index cannot support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class)
                .get(unitDistrictTable.getUnitId());
        if (unitObject == null){
            log.error("AdUnitDistrictTable index error: {}",
                    unitDistrictTable.getUnitId());
            return;
        }

        String key = CommonUtils.stringConcat(
                unitDistrictTable.getProvince(),
                unitDistrictTable.getCity()
        );
        Set<Long> value = new HashSet<>(Collections.singleton(unitDistrictTable.getUnitId()));
        handleBinlogEvent(DataTable.of(UnitDistrictIndex.class),
                key, value,
                type
        );
    }


    public static void handleLevel4(AdUnitItTable unitItTable, OpType type) {

        if (type == OpType.UPDATE) {
            log.error("it index can not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(
                AdUnitIndex.class
        ).get(unitItTable.getUnitId());
        if (unitObject == null) {
            log.error("AdUnitItTable index error: {}",
                    unitItTable.getUnitId());
            return;
        }

        Set<Long> value = new HashSet<>(
                Collections.singleton(unitItTable.getUnitId())
        );
        handleBinlogEvent(
                DataTable.of(UnitItIndex.class),
                unitItTable.getItTag(),
                value,
                type
        );
    }

    public static void handleLevel4(AdUnitKeywordTable keywordTable,
                                    OpType type) {

        if (type == OpType.UPDATE) {
            log.error("keyword index can not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(
                AdUnitIndex.class
        ).get(keywordTable.getUnitId());
        if (unitObject == null) {
            log.error("AdUnitKeywordTable index error: {}",
                    keywordTable.getUnitId());
            return;
        }

        Set<Long> value = new HashSet<>(
                Collections.singleton(keywordTable.getUnitId())
        );
        handleBinlogEvent(
                DataTable.of(UnitKeywordIndex.class),
                keywordTable.getKeyword(),
                value,
                type
        );
    }


    /**
     * 实现监听Binlog构造增量索引和加载全量索引
     * @param index
     * @param key
     * @param value
     * @param type
     * @param <K>
     * @param <V>
     */
   private static <K, V> void handleBinlogEvent(
           IndexAware<K, V> index,
           K key,
           V value,
           OpType type){
       switch (type){
           case ADD:
               index.add(key, value);
               break;
           case UPDATE:
               index.update(key, value);
               break;
           case DELETE:
               index.delete(key, value);
               break;
           default:
               break;
       }
   }


}