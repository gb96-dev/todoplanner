package com.sparta.todoplanner.service;

import com.sparta.todoplanner.dto.ScheduleRequestDto;
import com.sparta.todoplanner.dto.ScheduleResponseDto;
import com.sparta.todoplanner.entity.Schedule;
import com.sparta.todoplanner.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // 1. RequestDto -> Entity 변환
        Schedule schedule = new Schedule();
        schedule.setTitle(requestDto.getTitle());
        schedule.setContent(requestDto.getContent());
        schedule.setAuthor(requestDto.getAuthor());
        schedule.setPassword(requestDto.getPassword());

        // 2. DB 저장
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // 3. Entity -> ResponseDto 변환 후 반환
        return new ScheduleResponseDto(savedSchedule);
    }
}