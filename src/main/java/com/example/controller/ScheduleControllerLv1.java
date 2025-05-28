package com.example.controller;

import com.example.dto.ScheduleRequestDtoLv1;
import com.example.dto.ScheduleResponseDtoLv1;
import com.example.service.ScheduleServiceLv1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleControllerLv1 {

    private final ScheduleServiceLv1 scheduleService;

    @PostMapping
    public ResponseEntity<String> createSchedule(@RequestBody ScheduleRequestDtoLv1 requestDto) {
        scheduleService.createSchedule(requestDto);
        return ResponseEntity.status(201).body("일정이 성공적으로 등록되었습니다.");
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDtoLv1>> getAllSchedules(
            @RequestParam(required = false) String writer,
            @RequestParam(required = false) String modifiedAt) {
        List<ScheduleResponseDtoLv1> schedules = scheduleService.findSchedules(writer, modifiedAt);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable Long id) {
        ScheduleResponseDtoLv1 schedule = scheduleService.findScheduleById(id);
        if (schedule == null) {
            return ResponseEntity.status(404).body("해당 일정이 존재하지 않습니다.");
        }
        return ResponseEntity.ok(schedule);
    }
}
