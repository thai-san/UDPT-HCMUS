package com.dino.event_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventUpdateTicketQuantityRequest {
    String eventId;
    int consumeQuantity = 0;
}
