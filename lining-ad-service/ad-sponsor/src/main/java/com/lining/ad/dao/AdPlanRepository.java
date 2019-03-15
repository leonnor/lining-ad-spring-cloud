package com.lining.ad.dao;

import com.lining.ad.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * className AdPlanRepository
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/15 15:20
 */
public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {

    AdPlan findByIdAndUserId(Long id, Long userId);

    List<AdPlan> findAllByIdInAndUserId(List<Long> ids, Long userId);

    AdPlan findByUserIdAndPlanName(Long userId, String planName);

    List<AdPlan> findAllByPlanStatus(Integer status);
}
