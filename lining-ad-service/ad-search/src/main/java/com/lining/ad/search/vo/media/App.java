package com.lining.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className App
 * description 终端信息
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/20 19:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class App {

    /** 应用编码*/
    private String appCode;
    /** 应用名称*/
    private String appName;
    /** 应用包名*/
    private String packageName;
    /** activity 名称 */
    private String activityName;
}
