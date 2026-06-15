package com.example.registration.service;

import com.example.registration.model.Activity;
import com.example.registration.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    public Optional<Activity> findById(Long id) {
        return activityRepository.findById(id);
    }

    public List<Activity> findByStatus(String status) {
        return activityRepository.findByStatus(status);
    }

    public List<Activity> search(String keyword) {
        return activityRepository.findByNameContaining(keyword);
    }

    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    public void deleteById(Long id) {
        activityRepository.deleteById(id);
    }

    public long count() {
        return activityRepository.count();
    }
}
