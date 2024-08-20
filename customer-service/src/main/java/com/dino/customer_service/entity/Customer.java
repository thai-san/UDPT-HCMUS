package com.dino.customer_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String email;
    String firstName;
    String lastName;
    String address;
    String gender;
    LocalDate dob;
}
