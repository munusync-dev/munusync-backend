package com.munusync.backend.dtos.response;

import com.munusync.backend.dtos.request.AddressRequestDto;
import com.munusync.backend.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TimeZone;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficeResponseDto {

    private int id;
    private String name;
    private String phone;
    private String email;
    private TimeZone timezone;
    private Address address;
}
