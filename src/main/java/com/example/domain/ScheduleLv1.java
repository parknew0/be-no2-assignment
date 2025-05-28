package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleLv1 {
    private Long scheduleId;
    private String title;
    private String content;
    private String writer;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
