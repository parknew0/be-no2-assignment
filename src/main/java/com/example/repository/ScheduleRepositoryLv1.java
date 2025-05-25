package com.example.repository;

import com.example.config.ConnectionManagerLv1;
import com.example.domain.ScheduleLv1;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ScheduleRepositoryLv1 {

    public void insertSchedule(ScheduleLv1 schedule) {
        String sql = "INSERT INTO schedule (TITLE, CONTENT, WRITER, PASSWORD, CREATED_AT, MODIFIED_AT) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManagerLv1.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, schedule.getTitle());
            pstmt.setString(2, schedule.getContent());
            pstmt.setString(3, schedule.getWriter());
            pstmt.setString(4, schedule.getPassword());
            pstmt.setObject(5, schedule.getCreatedAt());
            pstmt.setObject(6, schedule.getModifiedAt());

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("일정 저장 실패", e);
        }
    }
}
