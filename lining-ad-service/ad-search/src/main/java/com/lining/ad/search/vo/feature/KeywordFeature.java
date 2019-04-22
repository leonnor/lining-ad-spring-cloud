package com.lining.ad.search.vo.feature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * className KeywordFeature
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/22 19:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeywordFeature {

    private List<String> keywords;
}
