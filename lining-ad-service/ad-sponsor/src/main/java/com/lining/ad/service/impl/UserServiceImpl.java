package com.lining.ad.service.impl;

import com.lining.ad.constant.Constants;
import com.lining.ad.dao.AdUserRepository;
import com.lining.ad.entity.AdUser;
import com.lining.ad.exception.AdException;
import com.lining.ad.service.IUserService;
import com.lining.ad.utils.CommonUtils;
import com.lining.ad.vo.CreateUserRequest;
import com.lining.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * className UserServiceImpl
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/15 17:57
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final AdUserRepository userRepository;

    @Autowired
    public UserServiceImpl(AdUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        //验证传入参数是否正确
        if (!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        //查找是否有同名用户
        AdUser oldUser = userRepository.findByUsername(request.getUsername());
        if (oldUser != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }
        //开始创建新用户
        AdUser newUser = userRepository.save(new AdUser(
                request.getUsername(),
                //设置用户的token
                CommonUtils.md5(request.getUsername())
        ));

        return new CreateUserResponse(newUser.getId(), newUser.getUsername(), newUser.getToken(),
                newUser.getCreateTime(), newUser.getUpdateTime());
    }
}
