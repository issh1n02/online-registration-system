package com.example.registration.controller;

import com.example.registration.model.Registration;
import com.example.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/list")
    public ResponseEntity<List<Registration>> list() {
        return ResponseEntity.ok(registrationService.findAll());
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<Registration>> getByActivityId(@PathVariable Long activityId) {
        return ResponseEntity.ok(registrationService.findByActivityId(activityId));
    }

    @PostMapping
    public ResponseEntity<Registration> register(@RequestBody Registration registration) {
        return ResponseEntity.ok(registrationService.register(registration));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        registrationService.cancel(id);
        return ResponseEntity.ok().build();
    }
}
