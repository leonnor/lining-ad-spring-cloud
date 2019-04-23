package com.lining.ad.index.creativeunit;

import com.lining.ad.index.IndexAware;
import com.lining.ad.index.adunit.AdUnitObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * className CreativeUnitIndex
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/28 14:37
 */
@Slf4j
@Component
public class CreativeUnitIndex implements IndexAware<String, CreativeUnitObject> {

    /** <adId-unitId, CreativeUnitObject>*/
    private static Map<String, CreativeUnitObject> objectMap;
    /** <adId, unitId Set>*/
    private static Map<Long, Set<Long>> creativeUnitMap;
    /** <unitId, adId Set>*/
    private static Map<Long, Set<Long>> unitCreativeMap;

    static {
        objectMap = new ConcurrentHashMap<>();
        creativeUnitMap = new ConcurrentHashMap<>();
        unitCreativeMap = new ConcurrentHashMap<>();
    }

    @Override
    public CreativeUnitObject get(String key) {
        return objectMap.get(key);
    }

    @Override
    public void add(String key, CreativeUnitObject value) {

         log.info("before add: {}", objectMap);
         objectMap.put(key, value);

         Set<Long> unitSet = creativeUnitMap.get(value.getAdId());
         if (CollectionUtils.isEmpty(unitSet)){
             unitSet = new ConcurrentSkipListSet<>();
             creativeUnitMap.put(value.getAdId(), unitSet);
         }
         unitSet.add(value.getUnitId());

         Set<Long> creativeSet = unitCreativeMap.get(value.getUnitId());
         if (CollectionUtils.isEmpty(creativeSet)){
             creativeSet = new ConcurrentSkipListSet<>();
             unitCreativeMap.put(value.getUnitId(), creativeSet);
         }
         creativeSet.add(value.getAdId());
        log.info("after add: {}", objectMap);
    }

    @Override
    public void update(String key, CreativeUnitObject value) {

        log.error("CreativeUnitIndex cannot support update");
    }

    @Override
    public void delete(String key, CreativeUnitObject value) {

        log.info("before delete: {}", objectMap);

        objectMap.remove(key);

        Set<Long> unitSet = creativeUnitMap.get(value.getAdId());
        if (CollectionUtils.isNotEmpty(unitSet)){
            unitSet.remove(value.getUnitId());
        }

        Set<Long> creativeSet = unitCreativeMap.get(value.getUnitId());
        if (CollectionUtils.isNotEmpty(creativeSet)){
            creativeSet.remove(value.getAdId());
        }

        log.info("after delete: {}", objectMap);
    }

    /**
     * 获取推广单元关联的创意id
     * @param unitObjects
     * @return
     */
    public List<Long> selectAds(List<AdUnitObject> unitObjects) {

        if (CollectionUtils.isEmpty(unitObjects)) {
            return Collections.emptyList();
        }

        List<Long> result = new ArrayList<>();

        for (AdUnitObject unitObject : unitObjects) {

            Set<Long> adIds = unitCreativeMap.get(unitObject.getUnitId());
            if (CollectionUtils.isNotEmpty(adIds)) {
                result.addAll(adIds);
            }
        }
        return result;
    }
}
