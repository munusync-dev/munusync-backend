package com.munusync.backend.dto.response;

import com.munusync.backend.enums.CompanyStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponse {

    private Long id;

    private String name;

    private String description;

    private String location;

    private String website;

    private List<String> tags;

    private CompanyStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
