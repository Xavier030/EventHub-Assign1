package com.eventhub.repository;

import com.eventhub.entity.Registration;
import com.eventhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Registration entity.
 */
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUser(User user);
    List<Registration> findByRegistrationDateBetween(LocalDateTime start, LocalDateTime end);
    List<Registration> findByUserId(Long userId);
}
