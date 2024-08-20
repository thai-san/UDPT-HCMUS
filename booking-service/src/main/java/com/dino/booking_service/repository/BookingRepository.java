package com.dino.booking_service.repository;

import com.dino.booking_service.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findByCustomer(String customer);

    List<Booking> findByEventId(String eventId);
}
