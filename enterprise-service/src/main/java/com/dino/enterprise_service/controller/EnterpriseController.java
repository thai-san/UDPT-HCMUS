package com.dino.enterprise_service.controller;

import com.dino.enterprise_service.dto.request.EnterpriseUpdateRequest;
import com.dino.enterprise_service.dto.response.APIResponse;
import com.dino.enterprise_service.dto.response.EnterpriseResponse;
import com.dino.enterprise_service.service.EnterpriseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EnterpriseController {
    EnterpriseService enterpriseService;

    @GetMapping
    APIResponse<List<EnterpriseResponse>> getEnterprises() {
        return APIResponse.<List<EnterpriseResponse>>builder()
                .result(enterpriseService.getEnterprises())
                .build();
    }

    @GetMapping("/get/{id}")
    APIResponse<EnterpriseResponse> getEnterprise(@PathVariable String id) {
        return APIResponse.<EnterpriseResponse>builder()
                .result(enterpriseService.getEnterprise(id))
                .build();
    }

    @GetMapping("/get-by-email/{email}")
    APIResponse<EnterpriseResponse> getEnterpriseByEmail(@PathVariable String email) {
        return APIResponse.<EnterpriseResponse>builder()
                .result(enterpriseService.getEnterpriseByEmail(email))
                .build();
    }

    @GetMapping("/my-info")
    APIResponse<EnterpriseResponse> getMyInfo() {
        return APIResponse.<EnterpriseResponse>builder()
                .result(enterpriseService.getMyInfo())
                .build();
    }

    @PutMapping("/{id}")
    APIResponse<EnterpriseResponse> updateEnterprise(@PathVariable String id, @RequestBody EnterpriseUpdateRequest request) {
        return APIResponse.<EnterpriseResponse>builder()
                .result(enterpriseService.updateEnterprise(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    APIResponse<String> deleteEnterprise(@PathVariable String id) {
        enterpriseService.deleteEnterprise(id);
        return APIResponse.<String>builder().result("Enterprise has been deleted").build();
    }
}
