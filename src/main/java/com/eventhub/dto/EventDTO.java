package com.eventhub.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
class EventDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime date;
    private boolean isActive;
    private String categoryName;
}