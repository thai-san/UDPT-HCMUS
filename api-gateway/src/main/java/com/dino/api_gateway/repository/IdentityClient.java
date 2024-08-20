package com.dino.api_gateway.repository;

import com.dino.api_gateway.dto.request.IntrospectRequest;
import com.dino.api_gateway.dto.response.APIResponse;
import com.dino.api_gateway.dto.response.IntrospectResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface IdentityClient {
    @PostExchange(url = "/auth/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<APIResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest request);
}
