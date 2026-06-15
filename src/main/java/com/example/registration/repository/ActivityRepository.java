package com.example.registration.repository;

import com.example.registration.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByStatus(String status);

    List<Activity> findByNameContaining(String keyword);
}
