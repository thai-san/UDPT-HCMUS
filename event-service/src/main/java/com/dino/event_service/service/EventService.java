package com.dino.event_service.service;

import com.dino.event_service.dto.request.EventUpdateTicketQuantityRequest;
import com.dino.event_service.exception.AppException;
import com.dino.event_service.exception.ErrorCode;
import com.dino.event_service.dto.request.EventCreationRequest;
import com.dino.event_service.dto.request.EventUpdateRequest;
import com.dino.event_service.dto.response.EventResponse;
import com.dino.event_service.entity.Event;
import com.dino.event_service.mapper.EventMapper;
import com.dino.event_service.repository.EventRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EventService {
    EventRepository eventRepository;
    EventMapper eventMapper;

    @PreAuthorize("hasRole('ENTERPRISE')")
    public EventResponse createEvent(EventCreationRequest request) {
        var context = SecurityContextHolder.getContext();
        String enterprise = context.getAuthentication().getName();

        Event event = eventMapper.toEvent(request);
        event.setEnterprise(enterprise);
        event = eventRepository.save(event);

        return eventMapper.toEventResponse(event);
    }

    public List<EventResponse> getEvents() {
        return eventRepository.findAll()
                .stream().map(eventMapper::toEventResponse).toList();
    }

    public EventResponse getEvent(String id) {
        if(id == null || id.isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);
        return eventMapper.toEventResponse(
                eventRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.EVENT_NOT_EXIST))
        );
    }

    @PreAuthorize("hasRole('ENTERPRISE')")
    public List<EventResponse> getMyEvents() {
        var context = SecurityContextHolder.getContext();
        String enterprise = context.getAuthentication().getName();

        return eventRepository.findByEnterprise(enterprise)
                .stream().map(eventMapper::toEventResponse).toList();
    }

    @PreAuthorize("hasRole('ENTERPRISE')")
    @PostAuthorize("returnObject.enterprise == authentication.name")
    public EventResponse updateEvent(String id, EventUpdateRequest request) {
        if(id == null || id.isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EVENT_NOT_EXIST));
        eventMapper.updateEvent(event, request);

        return eventMapper.toEventResponse(eventRepository.save(event));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    public EventResponse updateQuantity(EventUpdateTicketQuantityRequest request) {
        if(request.getEventId() == null || request.getEventId().isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new AppException(ErrorCode.EVENT_NOT_EXIST));

        event.setTicketQuantity(event.getTicketQuantity() - request.getConsumeQuantity());

        return eventMapper.toEventResponse(eventRepository.save(event));
    }

    @PreAuthorize("hasRole('ENTERPRISE')")
    public void deleteEvent(String id) {
        if(id == null || id.isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);

        var context = SecurityContextHolder.getContext();
        String enterprise = context.getAuthentication().getName();

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EVENT_NOT_EXIST));

        if(enterprise.equals(event.getEnterprise()))
            eventRepository.deleteById(id);
        else throw new AccessDeniedException("");
    }
}
