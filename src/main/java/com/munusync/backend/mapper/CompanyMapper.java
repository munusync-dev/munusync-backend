package com.munusync.backend.mapper;

import com.munusync.backend.dto.request.CompanyCreateRequest;
import com.munusync.backend.dto.request.CompanyUpdateRequest;
import com.munusync.backend.dto.response.CompanyResponse;
import com.munusync.backend.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);
    Company toEntity(CompanyCreateRequest dto);

    void updateEntityFromDto(CompanyUpdateRequest dto, @MappingTarget Company entity);
    CompanyResponse toResponse(Company entity);
}
