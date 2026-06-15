package com.example.registration.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> index() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "UP");
        result.put("service", "Online Registration System");
        result.put("version", "1.0.0");
        result.put("time", LocalDateTime.now().toString());
        return ResponseEntity.ok(result);
    }
}
