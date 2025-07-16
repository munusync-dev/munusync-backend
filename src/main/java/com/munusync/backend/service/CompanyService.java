package com.munusync.backend.service;

import com.munusync.backend.entity.Company;
import com.munusync.backend.exception.ResourceNotFoundException;
import com.munusync.backend.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }

    public Company update(Long id, Company updatedCompany) {
        Company existing = getById(id);
        existing.setName(updatedCompany.getName());
        existing.setDescription(updatedCompany.getDescription());
        existing.setStatus(updatedCompany.getStatus());
        existing.setNumberOfEmployees(updatedCompany.getNumberOfEmployees());
        existing.setSector(updatedCompany.getSector());
        existing.setWebsite(updatedCompany.getWebsite());
        existing.setHeadquarters(updatedCompany.getHeadquarters());
        existing.setFoundedYear(updatedCompany.getFoundedYear());
        existing.setLogoUrl(updatedCompany.getLogoUrl());
        existing.setTags(updatedCompany.getTags());
        return companyRepository.save(existing);
    }

    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
