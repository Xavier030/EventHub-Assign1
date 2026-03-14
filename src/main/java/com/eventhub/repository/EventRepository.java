package com.eventhub.repository;

import com.eventhub.model.Event;
import java.util.List;

public interface EventRepository {

    List<Event> findAll();

    Event save(Event event);

    Event findById(Long id);

}
