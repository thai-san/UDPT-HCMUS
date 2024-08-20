package com.dino.enterprise_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnterpriseResponse {
    String id;
    String email;
    String name;
    String address;
    String phone;
}
