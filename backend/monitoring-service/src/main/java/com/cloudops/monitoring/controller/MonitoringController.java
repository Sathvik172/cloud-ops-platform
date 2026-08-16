package com.cloudops.monitoring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class MonitoringController {

    @GetMapping("/monitoring/health")
    public Map<String, String> health() {
        return Map.of("service", "monitoring-service", "status", "UP");
    }

    @GetMapping("/monitoring/info")
    public Map<String, String> info() {
        return Map.of("service", "monitoring-service", "version", "1.0");
    }
}
