package com.lining.ad.service;

import com.lining.ad.exception.AdException;
import com.lining.ad.vo.*;

/**
 * className IAdUnitService
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/16 10:37
 */
public interface IAdUnitService {

    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;

    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException;
}
