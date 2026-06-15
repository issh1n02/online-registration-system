package com.example.registration.controller;

import com.example.registration.model.Activity;
import com.example.registration.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/list")
    public ResponseEntity<List<Activity>> list() {
        List<Activity> activities = activityService.findAll();
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getById(@PathVariable Long id) {
        return activityService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Activity>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(activityService.search(keyword));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Activity>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(activityService.findByStatus(status));
    }

    @PostMapping
    public ResponseEntity<Activity> create(@RequestBody Activity activity) {
        return ResponseEntity.ok(activityService.save(activity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
