package com.dino.identity_service.repository.httpclient;

import com.dino.identity_service.configuration.AuthenticationRequestInterceptor;
import com.dino.identity_service.dto.request.CustomerCreationRequest;
import com.dino.identity_service.dto.request.EnterpriseCreationRequest;
import com.dino.identity_service.dto.response.APIResponse;
import com.dino.identity_service.dto.response.CustomerResponse;
import com.dino.identity_service.dto.response.EnterpriseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "enterprise-service",
        url = "${app.services.enterprise}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface EnterpriseClient {
    @PostMapping(value = "/internal/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    APIResponse<EnterpriseResponse> createEnterprise(@RequestBody EnterpriseCreationRequest request);
}
