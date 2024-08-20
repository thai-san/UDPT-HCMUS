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
public class BookingResponse {
    String id;
    String customer;
    String eventId;
    LocalDate bookingDate;
    int quantity;
    boolean status;
    float totalPrice;
}
