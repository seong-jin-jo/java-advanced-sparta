package com.sparta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableJpaRepositories // JPA 리포지토리 활성화
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}

@Component
class PropertyPrinter implements CommandLineRunner {

    @Value("${whoishigher}")
    private String result;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(result);
    }
}