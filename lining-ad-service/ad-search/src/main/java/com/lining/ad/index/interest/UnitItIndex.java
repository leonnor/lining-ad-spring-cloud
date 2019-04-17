package com.lining.ad.index.interest;

import com.lining.ad.index.IndexAware;
import com.lining.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * className UnitItIndex
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/27 20:23
 */
@Slf4j
@Component
public class UnitItIndex implements IndexAware<String, Set<Long>> {

    /** 倒排索引map， 通过兴趣标签查询关联的推广单元集合*/
    private static Map<String, Set<Long>> itUnitMap;

    /** 正排索引map， 通过推广单元id查询兴趣标签的集合*/
    private static Map<Long, Set<String>> unitItMap;

    static {
        itUnitMap = new ConcurrentHashMap<>();
        unitItMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        return itUnitMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> value) {

        log.info("UnitItIndex before add: {}", unitItMap);

        Set<Long> unitIdSet = CommonUtils.getorCreate(key, itUnitMap, ConcurrentSkipListSet::new);
        unitIdSet.addAll(value);

        for (Long unitId : value){
            Set<String> its = CommonUtils.getorCreate(unitId, unitItMap, ConcurrentSkipListSet::new);
            its.add(key);
        }

        log.info("UnitItIndex after add: {}", unitItMap);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("it index cannot support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {

        log.info("UnitItIndex, before delete: {}", unitItMap);
        Set<Long> unitIds = CommonUtils.getorCreate(key, itUnitMap,
                ConcurrentSkipListSet::new);
        unitIds.removeAll(value);

        for (Long unitId : value){
            Set<String> its = CommonUtils.getorCreate(unitId, unitItMap,
                    ConcurrentSkipListSet::new);
            its.remove(key);
        }

        log.info("UnitItIndex, after delete: {}", unitItMap);
    }

    /**
     * 匹配方法，通过传递进来的兴趣标签匹配某个推广单元是否包含了这些兴趣标签
     * @param unitId
     * @param itTags
     * @return
     */
    public boolean match(Long unitId, List<String> itTags) {

        if (unitItMap.containsKey(unitId) && CollectionUtils.isNotEmpty(unitItMap.get(unitId))){
            Set<String> unitIts = unitItMap.get(unitId);
            return CollectionUtils.isSubCollection(itTags, unitIts);
        }
        return false;
    }
}
