package com.dino.customer_service.repository;

import com.dino.customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    // boolean existsByEmail(String email);

    Optional<Customer> findByEmail(String email);
}
