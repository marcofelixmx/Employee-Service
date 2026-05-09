package com.invex.employee.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String secondName;
    private String paternalSurname;
    private String maternalSurname;
    private String sex;
    private LocalDate birthDate;
    private String position;
    private LocalDateTime createdAt;
    private Boolean active;

    public Integer getAge() {
        if (this.birthDate == null) {
            return null;
        }
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }
}
