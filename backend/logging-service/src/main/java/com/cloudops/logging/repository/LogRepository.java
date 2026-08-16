package com.cloudops.logging.repository;

import com.cloudops.logging.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntry, Long> {
}
