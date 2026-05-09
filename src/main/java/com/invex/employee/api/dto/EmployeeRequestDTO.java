package com.invex.employee.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {

    @NotBlank(message = "First name is mandatory")
    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String secondName;

    @NotBlank(message = "Paternal surname is mandatory")
    @Size(max = 50)
    private String paternalSurname;

    @NotBlank(message = "Maternal surname is mandatory")
    @Size(max = 50)
    private String maternalSurname;

    @NotBlank(message = "Sex is mandatory")
    private String sex;

    @NotNull(message = "Birth date is mandatory")
    @Past(message = "Birth date must be in the past")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @io.swagger.v3.oas.annotations.media.Schema(type = "string", example = "15-05-1995", pattern = "dd-MM-yyyy", description = "Date format: dd-MM-yyyy")
    private LocalDate birthDate;

    @NotBlank(message = "Position is mandatory")
    private String position;

    @NotNull(message = "Active status is mandatory")
    private Boolean active;
}
