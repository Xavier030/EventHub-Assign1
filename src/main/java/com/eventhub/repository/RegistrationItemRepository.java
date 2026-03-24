package com.eventhub.repository;

import com.eventhub.entity.RegistrationItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for RegistrationItem entity.
 */
public interface RegistrationItemRepository extends JpaRepository<RegistrationItem, Long> { }
