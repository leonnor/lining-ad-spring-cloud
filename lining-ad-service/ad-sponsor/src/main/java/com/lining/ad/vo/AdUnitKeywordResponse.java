package com.lining.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * className AdUnitKeywordResponse
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/16 19:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitKeywordResponse {

    private List<Long> ids;
}
