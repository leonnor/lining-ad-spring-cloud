package com.lining.ad.client.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * className AdPlan
 * description 响应对象，实现数据的反序列化
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/25 16:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlan {

    private Long id;
    private Long userId;
    private String planName;
    private Integer planStatus;
    private Date startDate;
    private Date endDate;
    private Date createTime;
    private Date updateTime;
}
