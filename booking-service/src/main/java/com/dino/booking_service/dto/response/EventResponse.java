package com.dino.booking_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventResponse {
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
