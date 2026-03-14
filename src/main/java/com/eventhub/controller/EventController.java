package com.eventhub.controller;

import com.eventhub.dto.CreateEventDTO;
import com.eventhub.dto.EventDTO;
import com.eventhub.service.EventService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDTO> getEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping
    public EventDTO createEvent(@Valid @RequestBody CreateEventDTO dto) {
        return eventService.createEvent(dto);
    }
}
