package com.dino.identity_service.controller;

import com.dino.identity_service.dto.request.EnterpriseCreationRequest;
import com.dino.identity_service.dto.response.APIResponse;
import com.dino.identity_service.dto.request.CustomerCreationRequest;
import com.dino.identity_service.dto.request.UserUpdateRequest;
import com.dino.identity_service.dto.response.UserResponse;
import com.dino.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping("/customer/registration")
    APIResponse<UserResponse> createCustomer(@RequestBody @Valid CustomerCreationRequest request) {
        return APIResponse.<UserResponse>builder()
                .result(userService.createCustomer(request))
                .build();
    }

    @PostMapping("/enterprise/registration")
    APIResponse<UserResponse> createEnterprise(@RequestBody @Valid EnterpriseCreationRequest request) {
        return APIResponse.<UserResponse>builder()
                .result(userService.createEnterprise(request))
                .build();
    }

    @GetMapping
    APIResponse<List<UserResponse>> getUsers() {
        return APIResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    APIResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        return APIResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @GetMapping("/my-info")
    APIResponse<UserResponse> getMyInfo() {
        return APIResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    APIResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return APIResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("{userId}")
    APIResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return APIResponse.<String>builder().result("User has been deleted").build();
    }
}
