package com.spartacoding.msa.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class EurekaClientApplication { // TODO 설정?

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

}
