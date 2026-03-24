package com.eventhub.service;

import com.eventhub.entity.Category;
import com.eventhub.entity.Event;
import java.math.BigDecimal;
import java.util.List;

/**
 * Service interface for Event operations.
 */
public interface EventService {

    List<Event> getAllEvents();

    Event getEventById(Long id);

    Event saveEvent(Event event);

    void deleteEvent(Long id);

    List<Event> getEventsByCategory(Category category);

    List<Event> getEventsInPriceRange(BigDecimal min, BigDecimal max);

    List<Event> getActiveEvents();

    List<Event> searchEvents(String keyword);
}
