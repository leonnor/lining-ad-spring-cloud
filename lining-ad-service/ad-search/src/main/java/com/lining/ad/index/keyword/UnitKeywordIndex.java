package com.lining.ad.index.keyword;

import com.lining.ad.index.IndexAware;
import com.lining.ad.untils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * className UnitKeywordIndex
 * description 关键词的索引实现
 * 对推广单元限制的索引都使用倒排索引来实现
 * @author ln
 * @version 1.0
 * @date 2019/3/26 20:58
 */
@Slf4j
@Component
public class UnitKeywordIndex implements IndexAware<String, Set<Long>> {

    /** 倒排索引map，通过关键词找到对应的推广计划id*/
    private static Map<String, Set<Long>> keywordUnitMap;
    /** 正排索引map， 通过推广计划id找到相应关键词*/
    private static Map<Long, Set<String>> unitKeywordMap;

    static {
        keywordUnitMap = new ConcurrentHashMap<>();
        unitKeywordMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {

        if (StringUtils.isEmpty(key)){
            return Collections.emptySet();
        }

        Set<Long> result = keywordUnitMap.get(key);
        if (result == null) {
            return Collections.emptySet();
        }
        return result;
    }

    @Override
    public void add(String key, Set<Long> value) {

        log.info("UnitKeywordIndex, before add: {}", unitKeywordMap);

        Set<Long> unitIdSet = CommonUtils.getorCreate(key, keywordUnitMap, ConcurrentSkipListSet::new);
        unitIdSet.addAll(value);

        for (Long unitId : value){
            Set<String> keywordSet = CommonUtils.getorCreate(unitId, unitKeywordMap,
                    ConcurrentSkipListSet::new);
            keywordSet.add(key);
        }

        log.info("UnitKeywordIndex, after add: {}", unitKeywordMap);
    }

    @Override
    public void update(String key, Set<Long> value) {
        /**
         * 由于关键词的更新会导致两个map发生改变，而且每个map都会牵扯到一个set服务，需要对set进行遍历
         * 所以更新成本非常高
         * 因此这里设置为不支持更新
         * 最好的更新方式是让用户/广告主直接删除后重新add
         */

        log.error("keyword index cannot support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {

        log.info("UnitKeywordIndex, before delete: {}", unitKeywordMap);
        /**
         * 注意这里的删除对应的key可能是一部分unitIds，并不是所有的
         * 所以不能直接通过map.remove
         */
        Set<Long> unitIds = CommonUtils.getorCreate(key, keywordUnitMap, ConcurrentSkipListSet::new);
        unitIds.removeAll(value);
        for (Long unitId : value){
            Set<String> keywordSet = CommonUtils.getorCreate(unitId, unitKeywordMap,
                    ConcurrentSkipListSet::new);
            keywordSet.remove(key);
        }

        log.info("UnitKeywordIndex, after delete: {}", unitKeywordMap);
    }

    /**
     * 匹配方法，通过传递进来的关键词匹配某个推广单元是否包含了这些关键词
     * @return
     */
    public boolean match(Long unitId, List<String> keywords){

        if (unitKeywordMap.containsKey(unitId) && CollectionUtils.isNotEmpty(unitKeywordMap.get(unitId))){

            Set<String> unitKeywords = unitKeywordMap.get(unitId);
            return CollectionUtils.isSubCollection(keywords, unitKeywords);
        }
        return false;
    }
}
