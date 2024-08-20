package com.dino.event_service.controller;

import com.dino.event_service.dto.request.EventCreationRequest;
import com.dino.event_service.dto.request.EventUpdateRequest;
import com.dino.event_service.dto.request.EventUpdateTicketQuantityRequest;
import com.dino.event_service.dto.response.APIResponse;
import com.dino.event_service.dto.response.EventResponse;
import com.dino.event_service.service.EventService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventController {
    EventService eventService;

    @PostMapping("/create")
    APIResponse<EventResponse> createEvent(@RequestBody @Valid EventCreationRequest request) {
        return APIResponse.<EventResponse>builder()
                .result(eventService.createEvent(request))
                .build();
    }

    @GetMapping("/get")
    APIResponse<List<EventResponse>> getEvents() {
        return APIResponse.<List<EventResponse>>builder()
                .result(eventService.getEvents())
                .build();
    }

    @GetMapping("/get/{id}")
    APIResponse<EventResponse> getEvent(@PathVariable("id") String id) {
        return APIResponse.<EventResponse>builder()
                .result(eventService.getEvent(id))
                .build();
    }

    @GetMapping("/my-events")
    APIResponse<List<EventResponse>> getMyEvents() {
        return APIResponse.<List<EventResponse>>builder()
                .result(eventService.getMyEvents())
                .build();
    }

    @PutMapping("/{id}")
    APIResponse<EventResponse> updateEvent(@PathVariable String id, @RequestBody EventUpdateRequest request) {
        return APIResponse.<EventResponse>builder()
                .result(eventService.updateEvent(id, request))
                .build();
    }

    @PutMapping("/update-quantity")
    APIResponse<EventResponse> updateQuantity(@RequestBody EventUpdateTicketQuantityRequest request) {
        return APIResponse.<EventResponse>builder()
                .result(eventService.updateQuantity(request))
                .build();
    }

    @DeleteMapping("/{id}")
    APIResponse<String> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return APIResponse.<String>builder().result("Event has been deleted").build();
    }
}
