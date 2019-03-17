package com.lining.ad.service.impl;

import com.lining.ad.constant.CommonStatus;
import com.lining.ad.constant.Constants;
import com.lining.ad.dao.AdPlanRepository;
import com.lining.ad.dao.AdUserRepository;
import com.lining.ad.entity.AdPlan;
import com.lining.ad.entity.AdUser;
import com.lining.ad.exception.AdException;
import com.lining.ad.service.IAdPlanService;
import com.lining.ad.utils.CommonUtils;
import com.lining.ad.vo.AdPlanGetRequest;
import com.lining.ad.vo.AdPlanResponse;
import com.lining.ad.vo.AdPlanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * className AdPlanServiceImpl
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/15 19:57
 */
@Service
public class AdPlanServiceImpl implements IAdPlanService {

    private final AdUserRepository userRepository;

    private final AdPlanRepository planRepository;

    @Autowired
    public AdPlanServiceImpl(AdUserRepository userRepository, AdPlanRepository planRepository) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {

        //检查参数是否正确
        if (!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        //确保关联的User存在
        Optional<AdUser> adUser = userRepository.findById(request.getUserId());
        if (!adUser.isPresent()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        //检查是否存在同名推广计划
        AdPlan oldPlan = planRepository.findByUserIdAndPlanName(request.getUserId(), request.getPlanName());
        if (oldPlan != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }

        //创建新计划
        AdPlan newAdPlan = planRepository.save(new AdPlan(request.getUserId(), request.getPlanName(),
                CommonUtils.parseStringDate(request.getStartDate()),
                CommonUtils.parseStringDate(request.getEndDate())));

        return new AdPlanResponse(newAdPlan.getId(), newAdPlan.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {

        if (!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        return planRepository.findAllByIdInAndUserId(request.getIds(), request.getUserId());
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {

        if (!request.updateValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (plan == null){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        if (request.getPlanName() != null){
            plan.setPlanName(request.getPlanName());
        }
        if (request.getStartDate() != null){
            plan.setStartDate(CommonUtils.parseStringDate(request.getStartDate()));
        }
        if (request.getEndDate() != null){
            plan.setEndDate(CommonUtils.parseStringDate(request.getEndDate()));
        }

        plan.setUpdateTime(new Date());
        plan = planRepository.save(plan);

        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAdPlan(AdPlanRequest request) throws AdException {

        if (!request.deleteValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (plan == null){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        planRepository.save(plan);
    }
}
