package com.eventhub.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class CreateEventDTO {

    @NotBlank
    @Size(min=3,max=100)
    private String title;

    @Size(max=1000)
    private String description;

    @PositiveOrZero
    private double ticketPrice;

    @NotBlank
    private String category;

}
