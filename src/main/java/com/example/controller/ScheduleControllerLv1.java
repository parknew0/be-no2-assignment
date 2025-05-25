package com.example.controller;

import com.example.dto.ScheduleRequestDtoLv1;
import com.example.dto.ScheduleResponseDtoLv1;
import com.example.service.ScheduleServiceLv1;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

import static spark.Spark.post;
import static spark.Spark.get;

public class ScheduleControllerLv1 {

    private static final ScheduleServiceLv1 scheduleService = new ScheduleServiceLv1();
    private static final Gson gson = new Gson();

    public static void registerRoutes() {
        post("/schedules", handleCreateSchedule);
        get("/schedules", handleGetAllSchedules);
        get("/schedules/:id", handleGetScheduleById);
    }

    private static final Route handleCreateSchedule = (Request req, Response res) -> {
        ScheduleRequestDtoLv1 requestDto = gson.fromJson(req.body(), ScheduleRequestDtoLv1.class);

        scheduleService.createSchedule(requestDto);

        res.status(201);
        return "일정이 성공적으로 등록되었습니다.";
    };

    private static final Route handleGetAllSchedules = (Request req, Response res) -> {
        String writer = req.queryParams("writer");
        String modifiedAt = req.queryParams("modifiedAt");

        List<ScheduleResponseDtoLv1> schedules = scheduleService.findSchedules(writer, modifiedAt);

        res.status(200);
        res.type("application/json");
        return gson.toJson(schedules);
    };

    private static final Route handleGetScheduleById = (Request req, Response res) -> {
        Long id = Long.parseLong(req.params(":id"));

        ScheduleResponseDtoLv1 schedule = scheduleService.findScheduleById(id);

        if (schedule == null) {
            res.status(404);
            return "해당 일정이 존재하지 않습니다.";
        }

        res.status(200);
        res.type("application/json");
        return gson.toJson(schedule);
    };
}
