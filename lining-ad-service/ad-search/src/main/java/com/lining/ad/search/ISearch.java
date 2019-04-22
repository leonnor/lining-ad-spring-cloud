package com.lining.ad.search;

import com.lining.ad.search.vo.SearchRequest;
import com.lining.ad.search.vo.SearchResponse;

/**
 * className ISearch
 * description 用于广告检索请求
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/20 16:37
 */
public interface ISearch {

    /**
     * 检索服务接口
     * @param request
     * @return
     */
    SearchResponse fetchAds(SearchRequest request);
}
