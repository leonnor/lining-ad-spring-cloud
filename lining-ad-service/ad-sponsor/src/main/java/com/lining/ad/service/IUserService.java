package com.lining.ad.service;

import com.lining.ad.exception.AdException;
import com.lining.ad.vo.CreateUserRequest;
import com.lining.ad.vo.CreateUserResponse;

/**
 * className IUserService
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/15 17:37
 */
public interface IUserService {

    /**
     * 创建用户
     * @param request
     * @return
     * @throws AdException
     */
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
