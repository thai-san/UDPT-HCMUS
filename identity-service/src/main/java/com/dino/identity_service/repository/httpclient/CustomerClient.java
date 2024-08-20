package com.dino.identity_service.repository.httpclient;

import com.dino.identity_service.configuration.AuthenticationRequestInterceptor;
import com.dino.identity_service.dto.request.CustomerCreationRequest;
import com.dino.identity_service.dto.response.APIResponse;
import com.dino.identity_service.dto.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "customer-service",
        url = "${app.services.customer}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface CustomerClient {
    @PostMapping(value = "/internal/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    APIResponse<CustomerResponse> createCustomer(@RequestBody CustomerCreationRequest request);
}
