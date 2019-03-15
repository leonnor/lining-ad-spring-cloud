package com.lining.ad.dao;

import com.lining.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * className AdUnitRepository
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/15 15:26
 */
public interface AdUnitRepository extends JpaRepository<AdUnit, Long> {

    AdUnit findByPlanIdAndUnitName(Long planId, String unitName);

    List<AdUnit> findAllByUnitStatus(Integer unitStatus);
}
