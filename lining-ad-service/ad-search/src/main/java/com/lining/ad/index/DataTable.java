package com.lining.ad.index;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * className DataTable
 * description 索引服务类缓存
 * 由于索引非常多，在使用时需要注入很多索引实现类
 * 这样会非常麻烦，所以这里实现一个索引服务类的缓存来获取全部的索引服务
 * @author ln
 * @version 1.0
 * @date 2019/3/28 15:11
 */
@Component
public class DataTable implements ApplicationContextAware, PriorityOrdered {

    private static ApplicationContext applicationContext;

    private static final Map<Class, Object> dataTableMap = new ConcurrentHashMap<>();

    /**
     * 通过应用程序上下文得到当前容器中已初始化的各个组件和Bean
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DataTable.applicationContext = applicationContext;
    }

    /**
     * 设置JavaBean初始化顺序
     * 由于DataTable是记录缓存的JavaBean
     * 因此要在缓存出现之前完成初始化
     * 这里设置为最先初始化
     * @return
     */
    @Override
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }

    /**
     * 获取缓存的方法
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("all")
    public static <T> T of(Class<T> clazz){

        T instance = (T) dataTableMap.get(clazz);
        if (null != instance){
            return instance;
        }

        dataTableMap.put(clazz, bean(clazz));
        return (T) dataTableMap.get(clazz);
    }

    /**
     * 通过beanName获取bean
     * @param beanName
     * @param <T>
     * @return
     */
    @SuppressWarnings("all")
    private static <T> T bean(String beanName){
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 通过索引类型获取bean
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("all")
    private static <T> T bean(Class clazz){
        return (T) applicationContext.getBean(clazz);
    }
}
