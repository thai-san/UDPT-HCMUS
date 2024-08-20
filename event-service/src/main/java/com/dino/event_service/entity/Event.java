package com.dino.event_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String enterprise;
    String name;
    String location;
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;
    float ticketPrice;
    int ticketQuantity;
    float promotion;
}
