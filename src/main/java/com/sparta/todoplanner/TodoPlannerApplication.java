package com.sparta.todoplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 이거 꼭 확인하세요!
@SpringBootApplication
public class TodoPlannerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoPlannerApplication.class, args);
    }
}