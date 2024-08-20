package com.dino.customer_service.controller;

import com.dino.customer_service.dto.request.CustomerCreationRequest;
import com.dino.customer_service.dto.response.APIResponse;
import com.dino.customer_service.dto.response.CustomerResponse;
import com.dino.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalCustomerController {
    CustomerService customerService;

    @PostMapping("/internal/registration")
    APIResponse<CustomerResponse> createCustomer(@RequestBody @Valid CustomerCreationRequest request) {
        return APIResponse.<CustomerResponse>builder()
                .result(customerService.createCustomer(request))
                .build();
    }
}
