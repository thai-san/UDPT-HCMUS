package com.dino.event_service.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventCreationRequest {
    String name;
    String location;
    LocalDate date = LocalDate.now();
    LocalTime startTime = LocalTime.now();
    LocalTime endTime = LocalTime.now();;
    float ticketPrice = 0;
    int ticketQuantity = 0;
    float promotion = 0;
}
