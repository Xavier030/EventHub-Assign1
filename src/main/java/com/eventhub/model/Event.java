package com.eventhub.model;

import jdk.jfr.Category;
import lombok.Data;
import java.time.LocalDate;

@Data
public class Event {

    private Long id;

    private String title;

    private String description;

    private double ticketPrice;

    private LocalDate eventDate;

    private Category category;

}
