package com.dino.enterprise_service.repository;

import com.dino.enterprise_service.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, String> {
    // boolean existsByEmail(String email);

    Optional<Enterprise> findByEmail(String email);
}
