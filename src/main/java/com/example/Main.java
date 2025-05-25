package com.example;

import static spark.Spark.port;
import static spark.Spark.init;

import com.example.controller.ScheduleControllerLv1;

public class Main {
    public static void main(String[] args) {
        port(8080); // 원하는 포트 설정
        ScheduleControllerLv1.registerRoutes(); // 컨트롤러에서 API 등록
        init(); // Spark 서버 초기화
    }
}
