package com.example.controller;

import com.example.dto.ScheduleRequestDtoLv1;
import com.example.service.ScheduleServiceLv1;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.post;

public class ScheduleControllerLv1 {

    private static final ScheduleServiceLv1 scheduleService = new ScheduleServiceLv1();
    private static final Gson gson = new Gson();

    public static void registerRoutes() {
        post("/schedules", handleCreateSchedule);
    }

    private static final Route handleCreateSchedule = (Request req, Response res) -> {
        ScheduleRequestDtoLv1 requestDto = gson.fromJson(req.body(), ScheduleRequestDtoLv1.class);

        scheduleService.createSchedule(requestDto);

        res.status(201);
        return "일정이 성공적으로 등록되었습니다.";
    };
}
