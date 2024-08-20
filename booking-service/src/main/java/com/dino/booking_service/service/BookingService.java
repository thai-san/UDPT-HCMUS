package com.dino.booking_service.service;

import com.dino.booking_service.dto.request.BookingCreationRequest;
import com.dino.booking_service.dto.request.BookingUpdateRequest;
import com.dino.booking_service.dto.request.EventUpdateTicketQuantityRequest;
import com.dino.booking_service.dto.response.BookingResponse;
import com.dino.booking_service.entity.Booking;
import com.dino.booking_service.mapper.BookingMapper;
import com.dino.booking_service.repository.BookingRepository;
import com.dino.booking_service.exception.AppException;
import com.dino.booking_service.exception.ErrorCode;
import com.dino.booking_service.repository.httpclient.EventClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookingService {
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    EventClient eventClient;

    @PreAuthorize("hasRole('CUSTOMER')")
    public BookingResponse createBooking(BookingCreationRequest request) {
        if(request.getEventId() == null || request.getEventId().isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);

        var result = eventClient.getEvent(request.getEventId());
        if(result.getCode() == 200) {
            Booking booking = bookingMapper.toBooking(request);

            if(booking.getQuantity() <= result.getResult().getTicketQuantity()) {
                var updateResult = eventClient.updateTicketQuantity(
                        EventUpdateTicketQuantityRequest.builder()
                                .eventId(booking.getEventId())
                                .consumeQuantity(booking.getQuantity())
                                .build()
                );

                if(updateResult.getCode() == 200) {
                    float totalPrice = booking.getQuantity()
                            * result.getResult().getTicketPrice()
                            * (1 - result.getResult().getPromotion());
                    totalPrice = (float)Math.ceil(totalPrice * 100) / 100;

                    var context = SecurityContextHolder.getContext();
                    String customer = context.getAuthentication().getName();

                    booking.setCustomer(customer);
                    booking.setTotalPrice(totalPrice);
                    booking = bookingRepository.save(booking);

                    return bookingMapper.toBookingResponse(booking);
                }
                else throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
            }
            else throw new AppException(ErrorCode.NOT_ENOUGH_QUANTITY);
        }
        else throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<BookingResponse> getBookings() {
        return bookingRepository.findAll()
                .stream().map(bookingMapper::toBookingResponse).toList();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostAuthorize("returnObject.customer == authentication.name")
    public BookingResponse getBooking(String id) {
        if(id == null || id.isEmpty()) throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);
        return bookingMapper.toBookingResponse(
                bookingRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_EXIST))
        );
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    public List<BookingResponse> getMyBookings() {
        var context = SecurityContextHolder.getContext();
        String customer = context.getAuthentication().getName();
        return bookingRepository.findByCustomer(customer)
                .stream().map(bookingMapper::toBookingResponse).toList();
    }

    @PreAuthorize("hasRole('ENTERPRISE')")
    public List<BookingResponse> getBookingsByEventId(String eventId) {
        if(eventId == null || eventId.isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);
        return bookingRepository.findByEventId(eventId)
                .stream().map(bookingMapper::toBookingResponse).toList();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostAuthorize("returnObject.customer == authentication.name")
    public BookingResponse updateEvent(BookingUpdateRequest request) {
        if(request.getId() == null || request.getId().isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);

        Booking booking = bookingRepository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_EXIST));
        bookingMapper.updateBooking(booking, request);
        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }
}
