package com.sparta.todoplanner.repository;

import com.sparta.todoplanner.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // 수정일 기준 내림차순 정렬 기능 추가
    List<Schedule> findAllByOrderByUpdatedAtDesc();
}