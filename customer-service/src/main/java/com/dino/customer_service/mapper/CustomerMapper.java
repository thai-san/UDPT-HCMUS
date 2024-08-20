package com.dino.customer_service.mapper;

import com.dino.customer_service.dto.request.CustomerCreationRequest;
import com.dino.customer_service.dto.request.CustomerUpdateRequest;
import com.dino.customer_service.dto.response.CustomerResponse;
import com.dino.customer_service.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerCreationRequest request);

    CustomerResponse toCustomerResponse(Customer customer);

    void updateCustomer(@MappingTarget Customer user, CustomerUpdateRequest request);
}
