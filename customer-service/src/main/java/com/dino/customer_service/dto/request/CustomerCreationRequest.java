package com.dino.customer_service.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerCreationRequest {
    @Email(message = "INVALID_EMAIL")
    String email;

    String firstName;
    String lastName;
    String address;
    String gender;
    LocalDate dob;
}
