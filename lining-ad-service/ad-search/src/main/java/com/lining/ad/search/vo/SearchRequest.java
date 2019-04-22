package com.lining.ad.search.vo;

import com.lining.ad.search.vo.feature.DistrictFeature;
import com.lining.ad.search.vo.feature.FeatureRelation;
import com.lining.ad.search.vo.feature.ItFeature;
import com.lining.ad.search.vo.feature.KeywordFeature;
import com.lining.ad.search.vo.media.AdSlot;
import com.lining.ad.search.vo.media.App;
import com.lining.ad.search.vo.media.Device;
import com.lining.ad.search.vo.media.Geo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * className SearchRequest
 * description 媒体方请求对象
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/20 16:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

    /** 媒体方请求标识*/
    private String mediaId;
    /** 请求基本信息*/
    private RequestInfo requestInfo;
    /** 匹配信息*/
    private FeatureInfo featureInfo;

    /**
     * 请求信息
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestInfo {

        private String requestId;

        private List<AdSlot> adSlots;
        private App app;
        private Geo geo;
        private Device device;
    }

    /**
     * 匹配信息
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FeatureInfo {

        private KeywordFeature keywordFeature;
        private DistrictFeature districtFeature;
        private ItFeature itFeature;

        private FeatureRelation relation = FeatureRelation.AND;
    }
}
