package com.lining.ad.vo;

import com.lining.ad.entity.unit_condition.AdUnitKeyword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * className AdUnitKeywordRequest
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/16 19:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitKeywordRequest {

    /** 允许批量创建，要定义一个内部类*/
    private List<UnitKeyword> unitKeywords;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static  class UnitKeyword{

        private Long unitId;
        private String keyword;
    }
}
