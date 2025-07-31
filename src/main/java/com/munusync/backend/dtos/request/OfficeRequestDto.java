package com.munusync.backend.dtos.request;

import com.munusync.backend.entity.Address;
import lombok.Builder;
import lombok.Data;

import java.util.TimeZone;

@Builder
@Data
public class OfficeRequestDto {

    private String name;
    private String phone;
    private String email;
    private TimeZone timezone;
    private Address address;
}
