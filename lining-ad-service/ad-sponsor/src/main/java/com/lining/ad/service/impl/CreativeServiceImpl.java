package com.lining.ad.service.impl;

import com.lining.ad.constant.Constants;
import com.lining.ad.dao.AdUserRepository;
import com.lining.ad.dao.CreativeRepository;
import com.lining.ad.entity.AdUser;
import com.lining.ad.entity.Creative;
import com.lining.ad.exception.AdException;
import com.lining.ad.service.ICreativeService;
import com.lining.ad.vo.CreativeRequest;
import com.lining.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * className CreativeServiceImpl
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/21 15:26
 */
@Service
public class CreativeServiceImpl implements ICreativeService {

    private final CreativeRepository creativeRepository;
    private final AdUserRepository userRepository;
    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository, AdUserRepository userRepository) {
        this.creativeRepository = creativeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) throws AdException{

        if (!request.creativeValidate()) {
           throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        Optional<AdUser> adUser = userRepository.findById(request.getUserId());
        if (!adUser.isPresent()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        Creative creative = creativeRepository.save(request.convertToEntity());
        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
