package com.example.service;

import com.example.domain.ScheduleLv1;
import com.example.dto.ScheduleRequestDtoLv1;
import com.example.repository.ScheduleRepositoryLv1;

import java.time.LocalDateTime;

public class ScheduleServiceLv1 {

    private final ScheduleRepositoryLv1 scheduleRepository = new ScheduleRepositoryLv1();

    public void createSchedule(ScheduleRequestDtoLv1 requestDto) {
        LocalDateTime now = LocalDateTime.now();

        ScheduleLv1 schedule = new ScheduleLv1(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getWriter(),
                requestDto.getPassword(),
                now,
                now
        );

        scheduleRepository.insertSchedule(schedule);
    }
}
