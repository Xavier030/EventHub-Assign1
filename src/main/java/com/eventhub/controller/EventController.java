package com.eventhub.controller;

import com.eventhub.entity.Category;
import com.eventhub.entity.Event;
import com.eventhub.service.EventService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST Controller for Event-related API endpoints.
 */
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Get all events
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // Get event by ID
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    // Create or update event
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Event createOrUpdateEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    // Delete event
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    // Get events by category
    @GetMapping("/category/{categoryId}")
    public List<Event> getEventsByCategory(@PathVariable Long categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        return eventService.getEventsByCategory(category);
    }

    // Get events by price range
    @GetMapping("/price")
    public List<Event> getEventsByPrice(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        return eventService.getEventsInPriceRange(min, max);
    }

    // Get active events
    @GetMapping("/active")
    public List<Event> getActiveEvents() {
        return eventService.getActiveEvents();
    }

    // Search events by keyword
    @GetMapping("/search")
    public List<Event> searchEvents(@RequestParam String keyword) {
        return eventService.searchEvents(keyword);
    }
}
