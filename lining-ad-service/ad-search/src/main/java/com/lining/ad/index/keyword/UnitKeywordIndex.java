package com.lining.ad.index.keyword;

import com.lining.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

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


    @Override
    public Set<Long> get(String key) {
        return null;
    }

    @Override
    public void add(String key, Set<Long> value) {

    }

    @Override
    public void update(String key, Set<Long> value) {

    }

    @Override
    public void delete(String key, Set<Long> value) {

    }
}
