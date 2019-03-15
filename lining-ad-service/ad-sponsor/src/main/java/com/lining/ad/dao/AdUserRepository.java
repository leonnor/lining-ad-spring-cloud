package com.lining.ad.dao;

import com.lining.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * className AdUserRepository
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/15 15:18
 */
public interface AdUserRepository extends JpaRepository<AdUser, Long> {

    /**
     * 根据用户名查找用户记录
     * @param username
     * @return
     */
    AdUser findByUsername(String username);
}
