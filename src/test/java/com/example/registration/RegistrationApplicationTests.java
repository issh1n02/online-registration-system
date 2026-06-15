package com.example.registration;

import com.example.registration.model.Activity;
import com.example.registration.model.Registration;
import com.example.registration.service.ActivityService;
import com.example.registration.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegistrationApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private RegistrationService registrationService;

    @Test
    void contextLoads() {
        // Verify Spring context loads successfully
    }

    @Test
    void testApplicationStartup() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/", String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("UP"));
    }

    @Test
    void testCreateActivity() {
        Activity activity = new Activity();
        activity.setName("JUnit Test Activity");
        activity.setDescription("Test activity for JUnit");
        activity.setLocation("Test Room");
        activity.setStartTime(LocalDateTime.now().plusDays(7));
        activity.setEndTime(LocalDateTime.now().plusDays(7).plusHours(3));
        activity.setMaxParticipants(50);
        activity.setStatus("ACTIVE");

        Activity saved = activityService.save(activity);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("JUnit Test Activity", saved.getName());
        assertEquals("ACTIVE", saved.getStatus());
    }

    @Test
    void testFindAllActivities() {
        List<Activity> activities = activityService.findAll();
        assertNotNull(activities);
        // Activities list may be empty in test environment
    }

    @Test
    void testActivityCount() {
        long count = activityService.count();
        assertTrue(count >= 0);
    }

    @Test
    void testCreateRegistration() {
        // First create an activity
        Activity activity = new Activity();
        activity.setName("Registration Test Activity");
        activity.setDescription("For registration test");
        activity.setLocation("Room 101");
        activity.setStartTime(LocalDateTime.now().plusDays(1));
        activity.setEndTime(LocalDateTime.now().plusDays(1).plusHours(2));
        activity.setMaxParticipants(30);
        activity.setStatus("ACTIVE");
        Activity savedActivity = activityService.save(activity);

        // Then create a registration for it
        Registration registration = new Registration();
        registration.setActivityId(savedActivity.getId());
        registration.setUserName("Test User");
        registration.setUserEmail("test@example.com");
        registration.setUserPhone("13800000000");

        Registration saved = registrationService.register(registration);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("Test User", saved.getUserName());
        assertEquals("CONFIRMED", saved.getStatus());
    }

    @Test
    void testCancelRegistration() {
        // Create activity
        Activity activity = new Activity();
        activity.setName("Cancel Test Activity");
        activity.setDescription("For cancel test");
        activity.setLocation("Room 202");
        activity.setStartTime(LocalDateTime.now().plusDays(2));
        activity.setEndTime(LocalDateTime.now().plusDays(2).plusHours(2));
        activity.setMaxParticipants(20);
        activity.setStatus("ACTIVE");
        Activity savedActivity = activityService.save(activity);

        // Create registration
        Registration registration = new Registration();
        registration.setActivityId(savedActivity.getId());
        registration.setUserName("Cancel User");
        registration.setUserEmail("cancel@example.com");
        Registration saved = registrationService.register(registration);

        // Cancel
        registrationService.cancel(saved.getId());

        // Verify
        Registration cancelled = registrationService.findById(saved.getId()).orElse(null);
        assertNotNull(cancelled);
        assertEquals("CANCELLED", cancelled.getStatus());
    }

    @Test
    void testHealthEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/", String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Online Registration System"));
    }

    @Test
    void testActivityListEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/activity/list", String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}
