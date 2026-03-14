package com.eventhub.service;

import com.eventhub.dto.CreateEventDTO;
import com.eventhub.dto.EventDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private List<EventDTO> events = new ArrayList<>();

    @Override
    public List<EventDTO> getAllEvents() {
        return events;
    }

    @Override
    public EventDTO createEvent(CreateEventDTO dto) {

        EventDTO event = new EventDTO();

        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setTicketPrice(dto.getTicketPrice());
        event.setCategory(dto.getCategory());

        events.add(event);

        return event;
    }
}
