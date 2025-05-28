package com.example.controller;

import com.example.dto.ScheduleUpdateRequestDtoLv2;
import com.example.dto.ScheduleDeleteRequestDtoLv2;
import com.example.domain.ScheduleLv1;
import com.example.service.ScheduleServiceLv2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleControllerLv2 {

    private final ScheduleServiceLv2 scheduleService;

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDtoLv2 requestDto) {
        boolean updated = scheduleService.updateSchedule(id, requestDto);
        if (!updated) {
            return ResponseEntity.status(403).body("비밀번호가 일치하지 않아 수정할 수 없습니다.");
        }
        return ResponseEntity.ok("일정이 성공적으로 수정되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleDeleteRequestDtoLv2 requestDto) {
        ScheduleLv1 schedule = scheduleService.findScheduleById(id);
        if (schedule == null) {
            return ResponseEntity.status(404).body("해당 일정이 존재하지 않습니다.");
        }
        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            return ResponseEntity.status(403).body("비밀번호가 일치하지 않아 삭제할 수 없습니다.");
        }

        scheduleService.deleteSchedule(id, requestDto);
        return ResponseEntity.ok("일정이 성공적으로 삭제되었습니다.");
    }
}
