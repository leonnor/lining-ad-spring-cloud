package com.lining.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className AdUnitKeywordTable
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/30 16:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitKeywordTable {

    private Long unitId;
    private String keyword;
}
