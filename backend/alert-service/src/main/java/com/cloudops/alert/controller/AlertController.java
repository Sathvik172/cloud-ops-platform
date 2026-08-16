package com.cloudops.alert.controller;

import com.cloudops.alert.entity.Alert;
import com.cloudops.alert.repository.AlertRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private final AlertRepository alertRepository;

    public AlertController(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @PostMapping
    public ResponseEntity<Alert> createAlert(@RequestBody Map<String, String> request) {

        Alert alert = new Alert(
                request.get("serviceName"),
                request.get("severity"),
                request.get("message")
        );

        return ResponseEntity.ok(alertRepository.save(alert));
    }

    @GetMapping
    public ResponseEntity<List<Alert>> getAlerts() {
        return ResponseEntity.ok(alertRepository.findAll());
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<Alert> resolveAlert(@PathVariable Long id) {

        return alertRepository.findById(id)
                .map(alert -> {
                    alert.setStatus("RESOLVED");
                    return ResponseEntity.ok(alertRepository.save(alert));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
