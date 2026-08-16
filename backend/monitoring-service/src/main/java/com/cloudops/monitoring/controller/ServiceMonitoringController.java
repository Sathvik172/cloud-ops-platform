package com.cloudops.monitoring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ServiceMonitoringController {

    private final RestClient restClient = RestClient.builder().build();

    @GetMapping("/monitoring/services")
    public Map<String, Object> monitorServices() {

        List<Map<String, String>> services = new ArrayList<>();

        services.add(checkService(
                "auth-service",
                "http://localhost:8082/actuator/health"
        ));

        services.add(checkService(
                "logging-service",
                "http://localhost:8083/actuator/health"
        ));

        services.add(checkService(
                "alert-service",
                "http://localhost:8084/actuator/health"
        ));

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("service", "cloud-ops-platform");
        response.put("services", services);

        return response;
    }

    private Map<String, String> checkService(String name, String url) {

        Map<String, String> result = new LinkedHashMap<>();

        result.put("name", name);
        result.put("url", url);

        try {
            String response = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(String.class);

            result.put("status", "UP");

        } catch (Exception e) {
            result.put("status", "DOWN");
        }

        return result;
    }
}
