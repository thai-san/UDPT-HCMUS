package com.dino.booking_service.repository.httpclient;

import com.dino.booking_service.configuration.AuthenticationRequestInterceptor;
import com.dino.booking_service.dto.request.EventUpdateTicketQuantityRequest;
import com.dino.booking_service.dto.response.EventResponse;
import com.dino.booking_service.dto.response.APIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "event-service",
        url = "${app.services.event}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface EventClient {
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    APIResponse<EventResponse> getEvent(@PathVariable String id);

    @PutMapping(value = "/update-quantity", produces = MediaType.APPLICATION_JSON_VALUE)
    APIResponse<EventResponse> updateTicketQuantity(@RequestBody EventUpdateTicketQuantityRequest request);
}
