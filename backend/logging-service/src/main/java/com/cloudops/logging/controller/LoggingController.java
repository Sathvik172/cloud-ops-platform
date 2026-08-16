package com.cloudops.logging.controller;

import com.cloudops.logging.entity.LogEntry;
import com.cloudops.logging.repository.LogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/logs")
public class LoggingController {

    private final LogRepository logRepository;

    public LoggingController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @PostMapping
    public ResponseEntity<LogEntry> createLog(@RequestBody Map<String, String> request) {

        LogEntry log = new LogEntry(
                request.get("serviceName"),
                request.get("level"),
                request.get("message")
        );

        return ResponseEntity.ok(logRepository.save(log));
    }

    @GetMapping
    public ResponseEntity<List<LogEntry>> getLogs() {
        return ResponseEntity.ok(logRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogEntry> getLog(@PathVariable Long id) {
        return logRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
