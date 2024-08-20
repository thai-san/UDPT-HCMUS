package com.dino.identity_service.mapper;

import com.dino.identity_service.dto.request.CustomerCreationRequest;
import com.dino.identity_service.dto.request.EnterpriseCreationRequest;
import com.dino.identity_service.dto.request.UserUpdateRequest;
import com.dino.identity_service.dto.response.UserResponse;
import com.dino.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User customerRequestToUser(CustomerCreationRequest request);

    User enterpriseRequestToUser(EnterpriseCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
