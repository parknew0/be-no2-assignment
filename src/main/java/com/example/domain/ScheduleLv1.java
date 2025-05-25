package com.example.domain;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleLv1 {
    private Long scheduleId;
    private String title;
    private String content;
    private String writer;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleLv1(String title, String content, String writer, String password,
                       LocalDateTime createdAt, LocalDateTime modifiedAt)
    {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
