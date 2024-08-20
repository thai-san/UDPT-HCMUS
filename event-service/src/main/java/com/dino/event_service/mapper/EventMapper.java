package com.dino.event_service.mapper;

import com.dino.event_service.dto.request.EventCreationRequest;
import com.dino.event_service.dto.request.EventUpdateRequest;
import com.dino.event_service.dto.response.EventResponse;
import com.dino.event_service.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event toEvent(EventCreationRequest request);

    EventResponse toEventResponse(Event event);

    void updateEvent(@MappingTarget Event event, EventUpdateRequest request);
}
