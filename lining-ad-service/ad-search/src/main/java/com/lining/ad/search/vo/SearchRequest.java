package com.lining.ad.search.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestInfo {
        private String requestId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FeatureInfo {

    }
}
