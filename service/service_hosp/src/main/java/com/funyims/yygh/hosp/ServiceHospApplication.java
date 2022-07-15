package com.funyims.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Fun_yims
 * @date 2022/07/10 14:39
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.funyims")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class,args);
    }
}
