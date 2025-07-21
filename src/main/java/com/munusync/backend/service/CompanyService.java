package com.munusync.backend.service;

import com.munusync.backend.dto.request.CompanyCreateRequest;
import com.munusync.backend.dto.request.CompanyUpdateRequest;
import com.munusync.backend.dto.response.CompanyResponse;
import com.munusync.backend.entity.Company;
import com.munusync.backend.enums.CompanyStatus;
import com.munusync.backend.exception.ResourceNotFoundException;
import com.munusync.backend.mapper.CompanyMapper;
import com.munusync.backend.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyResponse createCompany(CompanyCreateRequest request) {
        Company company = companyMapper.toEntity(request);
        company.setStatus(CompanyStatus.ACTIVE);
        company.setCreatedAt(LocalDateTime.now());
        company.setUpdatedAt(LocalDateTime.now());
        Company savedCompany = companyRepository.save(company);
        return companyMapper.toResponse(savedCompany);
    }

    @Transactional(readOnly = true)
    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public CompanyResponse getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        return companyMapper.toResponse(company);
    }

    public CompanyResponse updateCompany(Long id, CompanyUpdateRequest request) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        companyMapper.updateEntityFromDto(request, existingCompany);
        existingCompany.setUpdatedAt(LocalDateTime.now());
        Company updatedCompany = companyRepository.save(existingCompany);
        return companyMapper.toResponse(updatedCompany);
    }

    public void deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Company not found with id: " + id);
        }
        companyRepository.deleteById(id);
    }
}