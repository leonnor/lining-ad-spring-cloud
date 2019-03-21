package com.lining.ad.service;

import com.lining.ad.exception.AdException;
import com.lining.ad.vo.CreativeRequest;
import com.lining.ad.vo.CreativeResponse;

/**
 * className ICreativeService
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/21 15:11
 */
public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request) throws AdException;
}
