package com.dino.event_service.repository;

import com.dino.event_service.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    // boolean existsByEmail(String email);

    List<Event> findByEnterprise(String enterprise);
}
