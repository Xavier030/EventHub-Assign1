package com.eventhub.repository;

import com.eventhub.entity.Category;
import com.eventhub.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

/**
 * Repository for Event entity.
 * Provides basic CRUD and custom query methods.
 */
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByCategory(Category category);

    List<Event> findByPriceBetween(BigDecimal min, BigDecimal max);

    List<Event> findByIsActiveTrue();

    @Query("SELECT e FROM Event e WHERE e.name LIKE %:keyword%")
    List<Event> searchByKeyword(@Param("keyword") String keyword);
}
