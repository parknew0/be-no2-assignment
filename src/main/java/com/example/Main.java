package com.example;

import static spark.Spark.port;
import static spark.Spark.init;

import com.example.controller.ScheduleControllerLv1;
import com.example.controller.ScheduleControllerLv2;

public class Main {
    public static void main(String[] args) {
        port(8080); // 원하는 포트 설정
        ScheduleControllerLv1.registerRoutes();
        ScheduleControllerLv2.registerRoutes();
        init(); // Spark 서버 초기화
    }
}
