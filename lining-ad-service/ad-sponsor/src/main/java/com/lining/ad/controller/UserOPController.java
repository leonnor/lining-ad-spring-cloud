package com.lining.ad.controller;

import com.alibaba.fastjson.JSON;
import com.lining.ad.exception.AdException;
import com.lining.ad.service.IUserService;
import com.lining.ad.vo.CreateUserRequest;
import com.lining.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * className UserOPController
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/21 17:07
 */
@Slf4j
@RestController
public class UserOPController {

    private final IUserService userService;

    @Autowired
    public UserOPController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create/user")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) throws AdException{

        log.info("ad-sponsor: createUser -> {}", JSON.toJSONString(request));
        return userService.createUser(request);
    }

}
