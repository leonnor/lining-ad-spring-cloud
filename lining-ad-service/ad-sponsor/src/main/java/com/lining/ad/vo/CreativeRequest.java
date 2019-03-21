package com.lining.ad.vo;

import com.lining.ad.constant.CommonStatus;
import com.lining.ad.entity.Creative;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * className CreativeRequest
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/21 15:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeRequest {

    private String name;
    private Integer type;
    private Integer materialType;
    private Integer height;
    private Integer width;
    private Long size;
    private Integer duration;
    private Long userId;
    private String url;

    /**
     * 实现将这个请求转变为实体类对象
     * @return
     */
    public Creative convertToEntity(){

        Creative creative = new Creative();
        creative.setName(name);
        creative.setType(type);
        creative.setMaterialType(materialType);
        creative.setHeight(height);
        creative.setWidth(width);
        creative.setSize(size);
        creative.setDuration(duration);
        creative.setAuditStatus(CommonStatus.VALID.getStatus());
        creative.setUserId(userId);
        creative.setUrl(url);
        creative.setCreateTime(new Date());
        creative.setUpdateTime(creative.getCreateTime());

        return creative;
    }

    public boolean creativeValidate(){

        return !StringUtils.isEmpty(name)
                && type != null
                && materialType != null
                && height != null
                && width != null
                && size != null
                && duration != null
                && userId != null
                && !StringUtils.isEmpty(url);
    }
}
