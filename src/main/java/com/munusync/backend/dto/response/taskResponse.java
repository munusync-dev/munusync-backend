package com.munusync.backend.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class taskResponse {

    public class TaskResponse {
        private Long id;
        private String title;
        private String description;
        private boolean completed;
    }

}
