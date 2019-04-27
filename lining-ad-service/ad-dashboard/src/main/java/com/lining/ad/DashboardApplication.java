package com.lining.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * className DashboardApplication
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/27 13:52
 */
@EnableEurekaClient
@SpringBootApplication
@EnableHystrixDashboard
public class DashboardApplication {

    public static void main(String[] args) {

        SpringApplication.run(DashboardApplication.class, args);
    }
}
