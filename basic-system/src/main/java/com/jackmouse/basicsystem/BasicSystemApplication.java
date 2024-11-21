package com.jackmouse.basicsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.jackmouse.basicsystem.mapper")
@EnableDiscoveryClient
public class BasicSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicSystemApplication.class, args);
    }

}
