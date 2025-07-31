package com.munusync.backend.service;

import com.munusync.backend.dtos.request.OfficeRequestDto;
import com.munusync.backend.dtos.response.OfficeResponseDto;
import com.munusync.backend.entity.Office;
import com.munusync.backend.repository.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficeService {

    private final OfficeRepository officeRepository;

    public Office createOffice(OfficeRequestDto request, int companyId) {
        Office office = new Office();
        office.setAddress(request.getAddress());
        office.setCompanyId(companyId);
        office.setName(request.getName());
        office.setPhone(request.getPhone());
        office.setEmail(request.getEmail());
        office.setTimezone(request.getTimezone());

        return officeRepository.save(office);
    }

    public ResponseEntity<OfficeResponseDto> getOfficesById(int Id) {
        Office office = (Office) officeRepository.findById(Id);
        if (office == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new OfficeResponseDto(
                office.getId(),
                office.getName(),
                office.getPhone(),
                office.getEmail(),
                office.getTimeZone(),
                office.getAddress()
        ));
    }

    public List<OfficeResponseDto> getAllOffices() {
        return officeRepository
                .findAll()
                .stream()
                .map(office -> new OfficeResponseDto(
                        office.getId(),
                        office.getName(),
                        office.getPhone(),
                        office.getEmail(),
                        office.getTimeZone(),
                        office.getAddress()
                ))
                .toList();
    }

}
