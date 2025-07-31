package com.munusync.backend.controller;

import com.munusync.backend.config.JwtUser;
import com.munusync.backend.dtos.request.OfficeRequestDto;
import com.munusync.backend.dtos.response.OfficeResponseDto;
import com.munusync.backend.entity.Office;
import com.munusync.backend.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruiter/offices")
@RequiredArgsConstructor
public class OfficeController {

    private final OfficeService officeService;

    @PostMapping
    public ResponseEntity<Office> createOffice(
            @RequestBody OfficeRequestDto request,
            @AuthenticationPrincipal JwtUser user
    )
    {
        Office createdOffice = officeService.createOffice(request, user.getCompanyId());
        return ResponseEntity.ok(createdOffice);
    }

    @GetMapping
    public ResponseEntity<List<OfficeResponseDto>> getAllOffices() {
        List<OfficeResponseDto> officeResponseDtos = officeService.getAllOffices();
        return ResponseEntity.ok(officeResponseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfficeResponseDto> getOffice(@PathVariable int id) {
        ResponseEntity<OfficeResponseDto> office = officeService.getOfficesById(id);
        if (office == null) {
            return ResponseEntity.notFound().build();
        }
        return office;
    }
}
