package com.eventhub.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime date;
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference(value = "category-events")
    private Category category;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "event-registrations")
    private List<RegistrationItem> registrationItems;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public List<RegistrationItem> getRegistrationItems() { return registrationItems; }
    public void setRegistrationItems(List<RegistrationItem> registrationItems) { this.registrationItems = registrationItems; }
}
