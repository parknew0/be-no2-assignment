package com.example.controller;

import com.example.dto.ScheduleUpdateRequestDtoLv2;
import com.example.dto.ScheduleDeleteRequestDtoLv2;
import com.example.service.ScheduleServiceLv2;
import com.example.domain.ScheduleLv1;

import com.google.gson.Gson;

import static spark.Spark.put;
import static spark.Spark.delete;

public class ScheduleControllerLv2 {

    private static final ScheduleServiceLv2 scheduleService = new ScheduleServiceLv2();
    private static final Gson gson = new Gson();

    public static void registerRoutes() {
        put("/schedules/:id", (req, res) -> {
            Long id = Long.parseLong(req.params(":id"));
            ScheduleUpdateRequestDtoLv2 requestDto = gson.fromJson(req.body(), ScheduleUpdateRequestDtoLv2.class);

            boolean updated = scheduleService.updateSchedule(id, requestDto);

            if (!updated) {
                res.status(403);
                return "비밀번호가 일치하지 않아 수정할 수 없습니다.";
            }

            res.status(200);
            return "일정이 성공적으로 수정되었습니다.";
        });

        delete("/schedules/:id", (req, res) -> {
            Long id = Long.parseLong(req.params(":id"));
            ScheduleDeleteRequestDtoLv2 requestDto = gson.fromJson(req.body(), ScheduleDeleteRequestDtoLv2.class);

            ScheduleLv1 schedule = scheduleService.findScheduleById(id);

            if (schedule == null) {
                res.status(404);
                return "해당 일정이 존재하지 않습니다.";
            }

            if (!schedule.getPassword().equals(requestDto.getPassword())) {
                res.status(403);
                return "비밀번호가 일치하지 않아 삭제할 수 없습니다.";
            }

            scheduleService.deleteSchedule(id, requestDto);
            res.status(200);
            return "일정이 성공적으로 삭제되었습니다.";
        });

    }
}
