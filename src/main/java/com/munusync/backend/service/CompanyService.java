package com.munusync.backend.service;

import com.munusync.backend.entity.Company;
import com.munusync.backend.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Company updateCompany(Long id, Company updatedCompany) {
        return companyRepository.findById(id).map(existing -> {
            existing.setName(updatedCompany.getName());
            existing.setDescription(updatedCompany.getDescription());
            existing.setSector(updatedCompany.getSector());
            existing.setStatus(updatedCompany.getStatus());
            existing.setNumberOfEmployees(updatedCompany.getNumberOfEmployees());
            existing.setWebsite(updatedCompany.getWebsite());
            existing.setHeadquarters(updatedCompany.getHeadquarters());
            existing.setFoundedYear(updatedCompany.getFoundedYear());
            existing.setLogoUrl(updatedCompany.getLogoUrl());
            existing.setTags(updatedCompany.getTags());
            existing.setUpdatedAt(new java.util.Date());
            return companyRepository.save(existing);
        }).orElse(null);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
