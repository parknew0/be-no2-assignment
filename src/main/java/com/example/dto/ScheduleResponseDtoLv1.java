package com.example.dto;
import com.example.domain.ScheduleLv1;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class ScheduleResponseDtoLv1 {
    private Long scheduleId;
    private String title;
    private String content;
    private String writer;
    private String createdAt;
    private String modifiedAt;

    public ScheduleResponseDtoLv1(ScheduleLv1 schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.writer = schedule.getWriter();
        this.createdAt = schedule.getCreatedAt().toString();
        this.modifiedAt = schedule.getModifiedAt().toString();
    }

    private String formatDate(java.time.LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
