package com.eventhub.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "registration_items")
public class RegistrationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "registration_id")
    @JsonBackReference
    private Registration registration;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference(value = "event-registrations")
    private Event event;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Registration getRegistration() { return registration; }
    public void setRegistration(Registration registration) { this.registration = registration; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
}
