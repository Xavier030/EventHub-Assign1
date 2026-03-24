package com.eventhub.service.impl;

import com.eventhub.entity.Category;
import com.eventhub.entity.Event;
import com.eventhub.repository.EventRepository;
import com.eventhub.service.EventService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation of EventService.
 */
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> getEventsByCategory(Category category) {
        return eventRepository.findByCategory(category);
    }

    @Override
    public List<Event> getEventsInPriceRange(BigDecimal min, BigDecimal max) {
        return eventRepository.findByPriceBetween(min, max);
    }

    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue();
    }

    @Override
    public List<Event> searchEvents(String keyword) {
        return eventRepository.searchByKeyword(keyword);
    }
}
