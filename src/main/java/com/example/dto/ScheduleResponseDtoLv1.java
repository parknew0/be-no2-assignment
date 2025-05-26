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

    private static final DateTimeFormatter DATE_TIME_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_ONLY_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ScheduleResponseDtoLv1(ScheduleLv1 schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.writer = schedule.getWriter();
        this.createdAt  = schedule.getCreatedAt().format(DATE_ONLY_FMT);
        this.modifiedAt = schedule.getModifiedAt().format(DATE_ONLY_FMT);
    }

}
