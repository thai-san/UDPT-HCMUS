package com.dino.booking_service.controller;

import com.dino.booking_service.dto.request.BookingCreationRequest;
import com.dino.booking_service.dto.request.BookingUpdateRequest;
import com.dino.booking_service.dto.response.BookingResponse;
import com.dino.booking_service.service.BookingService;
import com.dino.booking_service.dto.response.APIResponse;
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
public class BookingController {
    BookingService bookingService;

    @PostMapping("/create")
    APIResponse<BookingResponse> createBooking(@RequestBody @Valid BookingCreationRequest request) {
        return APIResponse.<BookingResponse>builder()
                .result(bookingService.createBooking(request))
                .build();
    }

    @GetMapping
    APIResponse<List<BookingResponse>> getBookings() {
        return APIResponse.<List<BookingResponse>>builder()
                .result(bookingService.getBookings())
                .build();
    }

    @GetMapping("/{id}")
    APIResponse<BookingResponse> getBooking(@PathVariable String id) {
        return APIResponse.<BookingResponse>builder()
                .result(bookingService.getBooking(id))
                .build();
    }

    @GetMapping("/my-bookings")
    APIResponse<List<BookingResponse>> getMyBookings() {
        return APIResponse.<List<BookingResponse>>builder()
                .result(bookingService.getMyBookings())
                .build();
    }

    @GetMapping("/event/{id}")
    APIResponse<List<BookingResponse>> getBookingsByEventId(@PathVariable String id) {
        return APIResponse.<List<BookingResponse>>builder()
                .result(bookingService.getBookingsByEventId(id))
                .build();
    }

    @PutMapping
    APIResponse<BookingResponse> updateEvent(@RequestBody BookingUpdateRequest request) {
        return APIResponse.<BookingResponse>builder()
                .result(bookingService.updateEvent(request))
                .build();
    }
}
