package com.example.service;

import com.example.domain.ScheduleLv1;
import com.example.dto.ScheduleRequestDtoLv1;
import com.example.repository.ScheduleRepositoryLv1;

import java.time.LocalDateTime;

import com.example.dto.ScheduleResponseDtoLv1;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ScheduleResponseDtoLv1> findSchedules(String writer, String modifiedAt) {
        List<ScheduleLv1> schedules = scheduleRepository.findSchedules(writer, modifiedAt);

        return schedules.stream()
                .map(ScheduleResponseDtoLv1::new)
                .collect(Collectors.toList());
    }

    public ScheduleResponseDtoLv1 findScheduleById(Long id) {
        ScheduleLv1 schedule = scheduleRepository.findScheduleById(id);
        return (schedule != null) ? new ScheduleResponseDtoLv1(schedule) : null;
    }

}
