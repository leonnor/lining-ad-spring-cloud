package com.lining.ad.service.impl;

import com.lining.ad.constant.Constants;
import com.lining.ad.dao.AdPlanRepository;
import com.lining.ad.dao.AdUnitRepository;
import com.lining.ad.entity.AdPlan;
import com.lining.ad.entity.AdUnit;
import com.lining.ad.exception.AdException;
import com.lining.ad.service.IAdUnitService;
import com.lining.ad.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * className AdUnitServiceImpl
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/16 12:24
 */
@Service



public class AdUnitServiceImpl implements IAdUnitService {

    private final AdPlanRepository planRepository;

    private final AdUnitRepository unitRepository;

    @Autowired
    public AdUnitServiceImpl(AdPlanRepository planRepository, AdUnitRepository unitRepository) {
        this.planRepository = planRepository;
        this.unitRepository = unitRepository;
    }

    @Override
    @Transactional
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {

        if (!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        Optional<AdPlan> adPlan = planRepository.findById(request.getPlanId());
        if (!adPlan.isPresent()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        AdUnit oldAdUnit = unitRepository.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());
        if (oldAdUnit != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
        }

        AdUnit newAdUnit = unitRepository.save(new AdUnit(request.getPlanId(), request.getUnitName(),
                request.getPositionType(), request.getBudget()));

        return new AdUnitResponse(newAdUnit.getId(), newAdUnit.getUnitName());
    }

    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {

        return null;
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitRequest request) throws AdException {

        return null;
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {

        return null;
    }

    /** 判断相关推广单元是否存在*/
    private boolean isRelatedUnitExist(List<Long> unitIds) {

        if (CollectionUtils.isEmpty(unitIds)){
            return false;
        }

        //id可能会重复，用HashSet去重
        return unitRepository.findAllById(unitIds).size() == new HashSet<>(unitIds).size();
    }
}
