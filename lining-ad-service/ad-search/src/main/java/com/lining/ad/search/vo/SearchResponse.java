package com.lining.ad.search.vo;

import com.lining.ad.index.creative.CreativeObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className SearchResponse
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/22 19:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {

    public Map<String, List<Creative>> adSlot2Ads = new HashMap<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Creative {

        private Long adId;
        private String adUrl;
        private Integer width;
        private Integer height;
        private Integer type;
        private Integer materialType;

        /** 展示检测url*/
        private List<String> showMonitorUrl = Arrays.asList("www.imooc.com", "www.imooc.com");

        /** 点击检测url*/
        private List<String> clickMonitorUrl = Arrays.asList("www.imooc.com", "www.imooc.com");
    }

    /**
     * 将创意的索引对象转换为这里的创意对象
     * @param object
     * @return
     */
    public static Creative convert(CreativeObject object){

        Creative creative = new Creative();
        creative.setAdId(object.getAdId());
        creative.setAdUrl(object.getAdUrl());
        creative.setWidth(object.getWidth());
        creative.setHeight(object.getHeight());
        creative.setType(object.getType());
        creative.setMaterialType(object.getMaterialType());

        return creative;
    }
}
