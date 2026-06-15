package com.example.registration.service;

import com.example.registration.model.Registration;
import com.example.registration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Registration> findAll() {
        return registrationRepository.findAll();
    }

    public Optional<Registration> findById(Long id) {
        return registrationRepository.findById(id);
    }

    public List<Registration> findByActivityId(Long activityId) {
        return registrationRepository.findByActivityId(activityId);
    }

    public Registration register(Registration registration) {
        registration.setCreatedAt(LocalDateTime.now());
        registration.setStatus("CONFIRMED");
        return registrationRepository.save(registration);
    }

    public void cancel(Long id) {
        registrationRepository.findById(id).ifPresent(reg -> {
            reg.setStatus("CANCELLED");
            registrationRepository.save(reg);
        });
    }

    public long countByActivityId(Long activityId) {
        return registrationRepository.countByActivityId(activityId);
    }
}
