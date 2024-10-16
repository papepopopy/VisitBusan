package com.project.VisitBusan.repository;

import com.project.VisitBusan.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
