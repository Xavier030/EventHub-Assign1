package com.eventhub.dto;

import lombok.Data;

@Data
public class EventDTO {

    private Long id;

    private String title;

    private String description;

    private double ticketPrice;

    private String category;

}
