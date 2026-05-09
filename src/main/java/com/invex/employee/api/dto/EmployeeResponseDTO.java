package com.invex.employee.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private String paternalSurname;
    private String maternalSurname;
    private Integer age;
    private String sex;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    
    private String position;
    
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    
    private Boolean active;
}
