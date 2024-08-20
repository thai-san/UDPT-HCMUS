package com.dino.booking_service.mapper;

import com.dino.booking_service.dto.request.BookingCreationRequest;
import com.dino.booking_service.dto.request.BookingUpdateRequest;
import com.dino.booking_service.dto.response.BookingResponse;
import com.dino.booking_service.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toBooking(BookingCreationRequest request);

    BookingResponse toBookingResponse(Booking booking);

    void updateBooking(@MappingTarget Booking booking, BookingUpdateRequest request);
}
