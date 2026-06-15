package com.example.registration.repository;

import com.example.registration.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<Registration> findByActivityId(Long activityId);

    List<Registration> findByUserName(String userName);

    long countByActivityId(Long activityId);
}
