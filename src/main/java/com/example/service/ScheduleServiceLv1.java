package com.example.service;

import com.example.domain.ScheduleLv1;
import com.example.dto.ScheduleRequestDtoLv1;
import com.example.dto.ScheduleResponseDtoLv1;
import com.example.repository.ScheduleRepositoryLv1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceLv1 {

    private final ScheduleRepositoryLv1 scheduleRepository;

    public void createSchedule(ScheduleRequestDtoLv1 requestDto) {
        LocalDateTime now = LocalDateTime.now();

        ScheduleLv1 schedule = new ScheduleLv1(
                null,
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
        return scheduleRepository.findSchedules(writer, modifiedAt).stream()
                .map(ScheduleResponseDtoLv1::new)
                .collect(Collectors.toList());
    }

    public ScheduleResponseDtoLv1 findScheduleById(Long id) {
        ScheduleLv1 schedule = scheduleRepository.findScheduleById(id);
        return (schedule != null) ? new ScheduleResponseDtoLv1(schedule) : null;
    }
}
