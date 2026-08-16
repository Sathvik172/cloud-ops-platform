package com.cloudops.monitoring.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class MetricsController {

    private final MeterRegistry meterRegistry;

    public MetricsController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/monitoring/metrics")
    public Map<String, Object> metrics() {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("service", "monitoring-service");

        Double cpuUsage = meterRegistry.get("system.cpu.usage").gauge().value();
        response.put("cpuUsagePercent", Math.round(cpuUsage * 10000.0) / 100.0);

        Double cpuCount = meterRegistry.get("system.cpu.count").gauge().value();
        response.put("cpuCores", cpuCount.intValue());

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemory = memoryMXBean.getHeapMemoryUsage();

        long usedBytes = heapMemory.getUsed();
        long maxBytes = heapMemory.getMax();

        double usedMB = usedBytes / (1024.0 * 1024.0);
        double maxMB = maxBytes / (1024.0 * 1024.0);
        double memoryUsagePercent = maxBytes > 0
                ? (usedBytes * 100.0) / maxBytes
                : 0.0;

        response.put("memoryUsedMB", Math.round(usedMB * 100.0) / 100.0);
        response.put("memoryMaxMB", Math.round(maxMB * 100.0) / 100.0);
        response.put("memoryUsagePercent", Math.round(memoryUsagePercent * 100.0) / 100.0);

        Double uptime = meterRegistry.get("process.uptime").gauge().value();
        response.put("uptimeSeconds", Math.round(uptime * 100.0) / 100.0);

        Double threads = meterRegistry.get("jvm.threads.live").gauge().value();
        response.put("liveThreads", threads.intValue());

        return response;
    }
}
