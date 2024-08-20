package com.dino.identity_service.service;

import com.dino.identity_service.dto.request.CustomerCreationRequest;
import com.dino.identity_service.dto.request.EnterpriseCreationRequest;
import com.dino.identity_service.dto.request.UserUpdateRequest;
import com.dino.identity_service.dto.response.UserResponse;
import com.dino.identity_service.entity.User;
import com.dino.identity_service.exception.AppException;
import com.dino.identity_service.exception.ErrorCode;
import com.dino.identity_service.mapper.UserMapper;
import com.dino.identity_service.repository.UserRepository;
import com.dino.identity_service.repository.httpclient.CustomerClient;
import com.dino.identity_service.repository.httpclient.EnterpriseClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    CustomerClient customerClient;
    EnterpriseClient enterpriseClient;

    public UserResponse createCustomer(CustomerCreationRequest request) {
        if(request.getEmail() == null ||
                request.getEmail().isEmpty() ||
                request.getPassword() == null ||
                request.getPassword().isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);
        if(userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);

        var result = customerClient.createCustomer(request);
        if(result.getCode() == 200) {
            User user = userMapper.customerRequestToUser(request);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole("CUSTOMER");
            user = userRepository.save(user);

            return userMapper.toUserResponse(user);
        } else throw new AppException(ErrorCode.CAN_NOT_CREATE_USER);

    }

    public UserResponse createEnterprise(EnterpriseCreationRequest request) {
        if(request.getEmail() == null ||
                request.getEmail().isEmpty() ||
                request.getPassword() == null ||
                request.getPassword().isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);
        if(userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);

        var result = enterpriseClient.createEnterprise(request);
        if(result.getCode() == 200) {
            User user = userMapper.enterpriseRequestToUser(request);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole("ENTERPRISE");
            user = userRepository.save(user);

            return userMapper.toUserResponse(user);
        } else throw new AppException(ErrorCode.CAN_NOT_CREATE_USER);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.email == authentication.name")
    public UserResponse getUser(String id) {
        if(id == null || id.isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST))
        );
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateUser(String id, UserUpdateRequest request) {
        if(id == null || id.isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String id) {
        if(id == null || id.isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);
        userRepository.deleteById(id);
    }
}
