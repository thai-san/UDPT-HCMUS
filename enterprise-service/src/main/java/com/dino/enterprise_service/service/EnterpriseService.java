package com.dino.enterprise_service.service;

import com.dino.enterprise_service.dto.request.EnterpriseCreationRequest;
import com.dino.enterprise_service.dto.request.EnterpriseUpdateRequest;
import com.dino.enterprise_service.dto.response.EnterpriseResponse;
import com.dino.enterprise_service.entity.Enterprise;
import com.dino.enterprise_service.exception.AppException;
import com.dino.enterprise_service.exception.ErrorCode;
import com.dino.enterprise_service.mapper.EnterpriseMapper;
import com.dino.enterprise_service.repository.EnterpriseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EnterpriseService {
    EnterpriseRepository enterpriseRepository;
    EnterpriseMapper enterpriseMapper;

    public EnterpriseResponse createEnterprise(EnterpriseCreationRequest request) {
        Enterprise enterprise = enterpriseMapper.toEnterprise(request);
        enterpriseRepository.save(enterprise);

        return enterpriseMapper.toEnterpriseResponse(enterprise);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<EnterpriseResponse> getEnterprises() {
        return enterpriseRepository.findAll()
                .stream().map(enterpriseMapper::toEnterpriseResponse).toList();
    }

    public EnterpriseResponse getEnterprise(String id) {
        if(id == null || id.isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);
        return enterpriseMapper.toEnterpriseResponse(
                enterpriseRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.ENTERPRISE_NOT_EXIST))
        );
    }

    public EnterpriseResponse getEnterpriseByEmail(String email) {
        if(email == null || email.isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);
        return enterpriseMapper.toEnterpriseResponse(
                enterpriseRepository.findByEmail(email)
                        .orElseThrow(() -> new AppException(ErrorCode.ENTERPRISE_NOT_EXIST))
        );
    }

    public EnterpriseResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        Enterprise enterprise = enterpriseRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.ENTERPRISE_NOT_EXIST));

        return enterpriseMapper.toEnterpriseResponse(enterprise);
    }

    @PreAuthorize("hasRole('ENTERPRISE')")
    @PostAuthorize("returnObject.email == authentication.name")
    public EnterpriseResponse updateEnterprise(String id, EnterpriseUpdateRequest request) {
        if(id == null || id.isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);

        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTERPRISE_NOT_EXIST));
        enterpriseMapper.updateEnterprise(enterprise, request);

        return enterpriseMapper.toEnterpriseResponse(enterpriseRepository.save(enterprise));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEnterprise(String id) {
        if(id == null || id.isEmpty())
            throw new AppException(ErrorCode.MISSING_REQUIRED_PARAM);

        enterpriseRepository.deleteById(id);
    }
}
