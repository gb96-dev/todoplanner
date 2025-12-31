package com.sparta.todoplanner.service;

import com.sparta.todoplanner.dto.ScheduleRequestDto;
import com.sparta.todoplanner.dto.ScheduleResponseDto;
import com.sparta.todoplanner.entity.Schedule;
import com.sparta.todoplanner.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // Lv 1. 일정 생성
    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule();
        schedule.setTitle(requestDto.getTitle());
        schedule.setContent(requestDto.getContent());
        schedule.setAuthor(requestDto.getAuthor());
        schedule.setPassword(requestDto.getPassword());

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule);
    }

    // Lv 2. 선택 일정 조회
    @Transactional(readOnly = true)
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 일정이 존재하지 않습니다. ID: " + id));
        return new ScheduleResponseDto(schedule);
    }

    // Lv 2. 전체 일정 목록 조회 (수정일 기준 내림차순)
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getSchedules() {
        return scheduleRepository.findAllByOrderByUpdatedAtDesc().stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }
}