package com.munusync.backend.dtos.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressRequestDto {

    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
