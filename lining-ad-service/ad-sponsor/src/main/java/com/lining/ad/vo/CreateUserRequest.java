package com.lining.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;


/**
 * className CreateUserRequest
 * description 创建user的请求参数
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/15 17:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String username;

    public boolean validate(){

        return !StringUtils.isEmpty(username);
    }

}
