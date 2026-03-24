package com.eventhub.repository;

import com.eventhub.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Category entity.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> { }
