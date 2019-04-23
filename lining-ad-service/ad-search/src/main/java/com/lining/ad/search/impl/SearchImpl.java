package com.lining.ad.search.impl;

import com.alibaba.fastjson.JSON;
import com.lining.ad.index.CommonStauts;
import com.lining.ad.index.DataTable;
import com.lining.ad.index.adunit.AdUnitIndex;
import com.lining.ad.index.adunit.AdUnitObject;
import com.lining.ad.index.creative.CreativeIndex;
import com.lining.ad.index.creative.CreativeObject;
import com.lining.ad.index.creativeunit.CreativeUnitIndex;
import com.lining.ad.index.district.UnitDistrictIndex;
import com.lining.ad.index.interest.UnitItIndex;
import com.lining.ad.index.keyword.UnitKeywordIndex;
import com.lining.ad.search.ISearch;
import com.lining.ad.search.vo.SearchRequest;
import com.lining.ad.search.vo.SearchResponse;
import com.lining.ad.search.vo.feature.DistrictFeature;
import com.lining.ad.search.vo.feature.FeatureRelation;
import com.lining.ad.search.vo.feature.ItFeature;
import com.lining.ad.search.vo.feature.KeywordFeature;
import com.lining.ad.search.vo.media.AdSlot;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;

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

            if (relation == FeatureRelation.AND){

                filterKeywordFeature(adUnitIdSet, keywordFeature);
                filterDistrictFeature(adUnitIdSet, districtFeature);
                filterItTagFeature(adUnitIdSet, itFeature);

                targetUnitIdSet = adUnitIdSet;

            } else  {

                targetUnitIdSet = getORRelationUnitIds(
                        adUnitIdSet,
                        keywordFeature,
                        districtFeature,
                        itFeature
                );
            }

            List<AdUnitObject> unitObjects =
                    DataTable.of(AdUnitIndex.class).fetch(targetUnitIdSet);

            filterAdUnitAndPlanStatus(unitObjects, CommonStauts.VALID);

            List<Long> adIds = DataTable.of(CreativeUnitIndex.class)
                    .selectAds(unitObjects);
            List<CreativeObject> creatives = DataTable.of(CreativeIndex.class)
                    .fetch(adIds);

            /** 通过AdSlot实现对CreativeObject的过滤*/
            filterCreativeByAdSlot(
                    creatives,
                    adSlot.getWidth(),
                    adSlot.getHeight(),
                    adSlot.getType()
            );

            adSlot2Ads.put(
                    adSlot.getAdSlotCode(), bulidCreativeResponse(creatives)
            );
        }
        log.error("fetchAds: {}-{}",
                JSON.toJSONString(request),
                JSON.toJSONString(response));

        return response;
    }

    /**
     * OR时的过滤方法
     * @param adUnitIdSet
     * @param keywordFeature
     * @param districtFeature
     * @param itFeature
     * @return
     */
    private Set<Long> getORRelationUnitIds(Set<Long> adUnitIdSet,
                                           KeywordFeature keywordFeature,
                                           DistrictFeature districtFeature,
                                           ItFeature itFeature) {

        if (CollectionUtils.isEmpty(adUnitIdSet)) {
            return Collections.emptySet();
        }

        /** 创建三个feature的副本*/
        Set<Long> keywordUnitIdSet = new HashSet<>(adUnitIdSet);
        Set<Long> districtUnitIdSet = new HashSet<>(adUnitIdSet);
        Set<Long> itUnitIdSet = new HashSet<>(adUnitIdSet);

        filterKeywordFeature(keywordUnitIdSet, keywordFeature);
        filterDistrictFeature(districtUnitIdSet, districtFeature);
        filterItTagFeature(itUnitIdSet, itFeature);

        /** 获取过滤完后的idSet， 返回它们的并集*/
        return new HashSet<>(
                CollectionUtils.union(
                        CollectionUtils.union(keywordUnitIdSet, districtUnitIdSet),
                        itUnitIdSet
                )
        );
    }

    /**
     * AND时的过滤方法
     * @param adUnitIds
     * @param keywordFeature
     */
    private void filterKeywordFeature(Collection<Long> adUnitIds, KeywordFeature keywordFeature){

        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }

        if (CollectionUtils.isNotEmpty(keywordFeature.getKeywords())) {

            CollectionUtils.filter(
                    adUnitIds,
                    adUnitId -> DataTable.of(UnitKeywordIndex.class)
                            .match(adUnitId, keywordFeature.getKeywords())
            );
        }
    }

    private void filterDistrictFeature(Collection<Long> adUnitIds, DistrictFeature districtFeature){

        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }

        if (CollectionUtils.isNotEmpty(districtFeature.getDistricts())) {

            CollectionUtils.filter(
                    adUnitIds,
                    adUnitId -> DataTable.of(UnitDistrictIndex.class)
                            .match(adUnitId,districtFeature.getDistricts())
            );
        }
    }

    private void filterItTagFeature(Collection<Long> adUnitIds, ItFeature itFeature) {

        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }

        if (CollectionUtils.isNotEmpty(itFeature.getIts())) {

            CollectionUtils.filter(
                    adUnitIds,
                    adUnitId -> DataTable.of(UnitItIndex.class)
                            .match(adUnitId, itFeature.getIts())
            );
        }
    }

    /**
     * 判断推广计划和推广单元的状态
     * @param unitObjects
     */
    private void filterAdUnitAndPlanStatus(List<AdUnitObject> unitObjects, CommonStauts stauts){

        if (CollectionUtils.isEmpty(unitObjects)) {
            return;
        }

        CollectionUtils.filter(
                unitObjects,
                object -> object.getUnitStatus().equals(stauts.getStatus())
                && object.getAdPlanObject().getPlanStatus().equals(stauts.getStatus())
        );
    }

    /**
     * 通过AdSlot的属性对创意过滤
     * @param creatives
     * @param width
     * @param height
     * @param type
     */
    private void filterCreativeByAdSlot(List<CreativeObject> creatives,
                                        Integer width,
                                        Integer height,
                                        List<Integer> type) {

        CollectionUtils.filter(
                creatives,
                creative ->
                        creative.getAuditStatus().equals(CommonStauts.VALID.getStatus())
                && creative.getWidth().equals(width)
                && creative.getHeight().equals(height)
                && type.contains(creative.getType())
        );
    }

    /**
     * 将多个创意对象转换成响应中的创意对象
     * @param creatives
     * @return
     */
    private List<SearchResponse.Creative> bulidCreativeResponse(
            List<CreativeObject> creatives
    ) {
        if (CollectionUtils.isEmpty(creatives)) {
            return Collections.emptyList();
        }

        CreativeObject randomObject = creatives.get(
                Math.abs(new Random().nextInt()) % creatives.size()
        );

        return Collections.singletonList(
                SearchResponse.convert(randomObject)
        );
    }
}
