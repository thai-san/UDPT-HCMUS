package com.dino.enterprise_service.mapper;

import com.dino.enterprise_service.dto.request.EnterpriseCreationRequest;
import com.dino.enterprise_service.dto.request.EnterpriseUpdateRequest;
import com.dino.enterprise_service.dto.response.EnterpriseResponse;
import com.dino.enterprise_service.entity.Enterprise;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EnterpriseMapper {
    Enterprise toEnterprise(EnterpriseCreationRequest request);

    EnterpriseResponse toEnterpriseResponse(Enterprise enterprise);

    void updateEnterprise(@MappingTarget Enterprise enterprise, EnterpriseUpdateRequest request);
}
