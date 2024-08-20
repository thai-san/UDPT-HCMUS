package com.dino.customer_service.service;

import com.dino.customer_service.dto.request.CustomerCreationRequest;
import com.dino.customer_service.dto.request.CustomerUpdateRequest;
import com.dino.customer_service.dto.response.CustomerResponse;
import com.dino.customer_service.entity.Customer;
import com.dino.customer_service.mapper.CustomerMapper;
import com.dino.customer_service.repository.CustomerRepository;
import com.dino.customer_service.exception.AppException;
import com.dino.customer_service.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public CustomerResponse createCustomer(CustomerCreationRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customerRepository.save(customer);

        return customerMapper.toCustomerResponse(customer);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<CustomerResponse> getCustomers() {
        return customerRepository.findAll()
                .stream().map(customerMapper::toCustomerResponse).toList();
    }

    @PostAuthorize("returnObject.email == authentication.name")
    public CustomerResponse getCustomer(String id) {
        if(id == null || id.isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);
        return customerMapper.toCustomerResponse(
                customerRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXIST))
        );
    }

    public CustomerResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXIST));

        return customerMapper.toCustomerResponse(customer);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostAuthorize("returnObject.email == authentication.name")
    public CustomerResponse updateCustomer(String id, CustomerUpdateRequest request) {
        if(id == null || id.isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXIST));
        customerMapper.updateCustomer(customer, request);

        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCustomer(String id) {
        if(id == null || id.isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);

        customerRepository.deleteById(id);
    }
}
