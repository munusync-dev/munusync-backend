package com.munusync.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyUpdateRequest {

    @NotBlank
    private String name;

    private String description;

    private String location;

    private String website;

    private List<String> tags;
}
