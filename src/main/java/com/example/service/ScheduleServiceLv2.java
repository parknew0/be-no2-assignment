package com.example.service;

import com.example.domain.ScheduleLv1;
import com.example.dto.ScheduleUpdateRequestDtoLv2;
import com.example.repository.ScheduleRepositoryLv1;
import com.example.dto.ScheduleDeleteRequestDtoLv2;

import java.time.LocalDateTime;

public class ScheduleServiceLv2 {

    private final ScheduleRepositoryLv1 scheduleRepository = new ScheduleRepositoryLv1();

    public boolean updateSchedule(Long id, ScheduleUpdateRequestDtoLv2 dto) {
        ScheduleLv1 schedule = scheduleRepository.findScheduleById(id);

        if (schedule == null || !schedule.getPassword().equals(dto.getPassword())) {
            return false;
        }

        schedule.setTitle(dto.getTitle());
        schedule.setWriter(dto.getWriter());
        schedule.setModifiedAt(LocalDateTime.now());

        scheduleRepository.updateSchedule(schedule);
        return true;
    }

    public boolean deleteSchedule(Long id, ScheduleDeleteRequestDtoLv2 dto) {
        ScheduleLv1 schedule = scheduleRepository.findScheduleById(id);

        if (schedule == null || !schedule.getPassword().equals(dto.getPassword())) {
            return false;
        }

        scheduleRepository.deleteSchedule(id);
        return true;
    }

    public ScheduleLv1 findScheduleById(Long id) {
        return scheduleRepository.findScheduleById(id);
    }

}
