package com.lining.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * className SearchApplication
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/25 16:37
 */
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableHystrixDashboard
@SpringBootApplication
public class SearchApplication {

    public static void main(String[] args) {

        SpringApplication.run(SearchApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
