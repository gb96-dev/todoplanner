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

    // Lv 3. 선택 일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        // 1. 해당 일정이 DB에 존재하는지 확인
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다. ID: " + id));

        // 2. 비밀번호 일치 여부 확인 (입력받은 비번 vs DB에 저장된 비번)
        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 일치한다면 수정 진행 (제목, 내용, 작성자)
        schedule.setTitle(requestDto.getTitle());
        schedule.setContent(requestDto.getContent());
        schedule.setAuthor(requestDto.getAuthor());

        // 4. 수정된 정보를 DTO로 변환해서 반환
        return new ScheduleResponseDto(schedule);
    }

    // Lv 4. 선택 일정 삭제
    @Transactional
    public String deleteSchedule(Long id, String password) {
        // 1. 해당 일정이 DB에 존재하는지 확인
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다. ID: " + id));

        // 2. 비밀번호 일치 여부 확인
        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 일치한다면 삭제
        scheduleRepository.delete(schedule);
        return "ID [" + id + "] 일정이 성공적으로 삭제되었습니다.";
    }

}