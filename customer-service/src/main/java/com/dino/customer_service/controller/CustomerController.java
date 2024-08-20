package com.dino.customer_service.controller;

import com.dino.customer_service.dto.request.CustomerUpdateRequest;
import com.dino.customer_service.dto.response.CustomerResponse;
import com.dino.customer_service.service.CustomerService;
import com.dino.customer_service.dto.response.APIResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
    CustomerService customerService;

    @GetMapping
    APIResponse<List<CustomerResponse>> getCustomers() {
        return APIResponse.<List<CustomerResponse>>builder()
                .result(customerService.getCustomers())
                .build();
    }

    @GetMapping("/{userId}")
    APIResponse<CustomerResponse> getCustomer(@PathVariable("userId") String userId) {
        return APIResponse.<CustomerResponse>builder()
                .result(customerService.getCustomer(userId))
                .build();
    }

    @GetMapping("/my-info")
    APIResponse<CustomerResponse> getMyInfo() {
        return APIResponse.<CustomerResponse>builder()
                .result(customerService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    APIResponse<CustomerResponse> updateCustomer(@PathVariable String userId, @RequestBody CustomerUpdateRequest request) {
        return APIResponse.<CustomerResponse>builder()
                .result(customerService.updateCustomer(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    APIResponse<String> deleteCustomer(@PathVariable String userId) {
        customerService.deleteCustomer(userId);
        return APIResponse.<String>builder().result("Customer has been deleted").build();
    }
}
