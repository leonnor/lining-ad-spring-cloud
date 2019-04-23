package com.lining.ad.search.impl;

import com.lining.ad.index.DataTable;
import com.lining.ad.index.adunit.AdUnitIndex;
import com.lining.ad.search.ISearch;
import com.lining.ad.search.vo.SearchRequest;
import com.lining.ad.search.vo.SearchResponse;
import com.lining.ad.search.vo.feature.DistrictFeature;
import com.lining.ad.search.vo.feature.FeatureRelation;
import com.lining.ad.search.vo.feature.ItFeature;
import com.lining.ad.search.vo.feature.KeywordFeature;
import com.lining.ad.search.vo.media.AdSlot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * className SearchImpl
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/23 14:13
 */
@Slf4j
@Service
public class SearchImpl implements ISearch {

    @Override
    public SearchResponse fetchAds(SearchRequest request) {

        /** 请求的广告位信息*/
        List<AdSlot> adSlots = request.getRequestInfo().getAdSlots();

        /** 获取三个Feature*/
        KeywordFeature keywordFeature =
                request.getFeatureInfo().getKeywordFeature();
        DistrictFeature districtFeature =
                request.getFeatureInfo().getDistrictFeature();
        ItFeature itFeature =
                request.getFeatureInfo().getItFeature();

        FeatureRelation relation = request.getFeatureInfo().getRelation();

        /** 构造响应对象*/
        SearchResponse response = new SearchResponse();
        Map<String, List<SearchResponse.Creative>> adSlot2Ads =
                response.getAdSlot2Ads();
        for (AdSlot adSlot : adSlots){

            Set<Long> targetUnitIdSet;

            /**
             * 根据流量类型获取初始 AdUnit
             */
            Set<Long> adUnitIdSet = DataTable.of(
                    AdUnitIndex.class
            ).match(adSlot.getPositionType());
        }
        return null;
    }
}
