package com.dino.enterprise_service.controller;

import com.dino.enterprise_service.dto.response.APIResponse;
import com.dino.enterprise_service.dto.request.EnterpriseCreationRequest;
import com.dino.enterprise_service.dto.response.EnterpriseResponse;
import com.dino.enterprise_service.service.EnterpriseService;
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
public class InternalEnterpriseController {
    EnterpriseService enterpriseService;

    @PostMapping("/internal/registration")
    APIResponse<EnterpriseResponse> createEnterprise(@RequestBody @Valid EnterpriseCreationRequest request) {
        return APIResponse.<EnterpriseResponse>builder()
                .result(enterpriseService.createEnterprise(request))
                .build();
    }
}
