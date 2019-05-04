package com.hycloud.auth.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan({"com.hycloud.auth.sso.storage.mapper"})
public class SSOServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSOServerApplication.class, args);
    }
}
