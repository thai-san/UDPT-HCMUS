package com.dino.enterprise_service.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnterpriseCreationRequest {
    @Email(message = "INVALID_EMAIL")
    String email;

    String name;
    String address;
    String phone;
}
