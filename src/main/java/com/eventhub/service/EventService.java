package com.eventhub.service;

import com.eventhub.dto.CreateEventDTO;
import com.eventhub.dto.EventDTO;

import java.util.List;

public interface EventService {

    List<EventDTO> getAllEvents();

    EventDTO createEvent(CreateEventDTO dto);

}
