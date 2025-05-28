package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 스프링부트 애플리케이션의 진입점
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);  // 내장 톰캣 실행 및 컴포넌트 스캔
    }
}
