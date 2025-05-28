package com.example.repository;

import com.example.domain.ScheduleLv1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepositoryLv1 {

    private final DataSource dataSource;

    @Autowired
    public ScheduleRepositoryLv1(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertSchedule(ScheduleLv1 schedule) {
        String sql = "INSERT INTO schedule (TITLE, CONTENT, WRITER, PASSWORD, CREATED_AT, MODIFIED_AT) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, schedule.getTitle());
            pstmt.setString(2, schedule.getContent());
            pstmt.setString(3, schedule.getWriter());
            pstmt.setString(4, schedule.getPassword());
            pstmt.setObject(5, schedule.getCreatedAt());
            pstmt.setObject(6, schedule.getModifiedAt());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("일정 저장 실패", e);
        }
    }

    public List<ScheduleLv1> findSchedules(String writer, String modifiedAt) {
        List<ScheduleLv1> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM schedule WHERE 1=1");

        if (writer != null) sql.append(" AND WRITER = ?");
        if (modifiedAt != null) sql.append(" AND DATE(MODIFIED_AT) = ?");

        sql.append(" ORDER BY MODIFIED_AT DESC");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            int idx = 1;
            if (writer != null) pstmt.setString(idx++, writer);
            if (modifiedAt != null) pstmt.setString(idx++, modifiedAt);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(new ScheduleLv1(
                        rs.getLong("SCHEDULE_ID"),
                        rs.getString("TITLE"),
                        rs.getString("CONTENT"),
                        rs.getString("WRITER"),
                        rs.getString("PASSWORD"),
                        rs.getTimestamp("CREATED_AT").toLocalDateTime(),
                        rs.getTimestamp("MODIFIED_AT").toLocalDateTime()
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("일정 목록 조회 실패", e);
        }

        return result;
    }

    public ScheduleLv1 findScheduleById(Long id) {
        String sql = "SELECT * FROM schedule WHERE SCHEDULE_ID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new ScheduleLv1(
                        rs.getLong("SCHEDULE_ID"),
                        rs.getString("TITLE"),
                        rs.getString("CONTENT"),
                        rs.getString("WRITER"),
                        rs.getString("PASSWORD"),
                        rs.getTimestamp("CREATED_AT").toLocalDateTime(),
                        rs.getTimestamp("MODIFIED_AT").toLocalDateTime()
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("일정 단건 조회 실패", e);
        }

        return null;
    }

    public void updateSchedule(ScheduleLv1 schedule) {
        String sql = "UPDATE schedule SET TITLE = ?, WRITER = ?, MODIFIED_AT = ? WHERE SCHEDULE_ID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, schedule.getTitle());
            pstmt.setString(2, schedule.getWriter());
            pstmt.setObject(3, schedule.getModifiedAt());
            pstmt.setLong(4, schedule.getScheduleId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("일정 수정 실패", e);
        }
    }

    public void deleteSchedule(Long id) {
        String sql = "DELETE FROM schedule WHERE SCHEDULE_ID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("일정 삭제 실패", e);
        }
    }
}
