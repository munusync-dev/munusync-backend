package com.munusync.backend.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class taskRequest {


        @NotBlank//task request refused if tittle was null/empty
        private String title;
        private String description;
        private boolean completed;

}
