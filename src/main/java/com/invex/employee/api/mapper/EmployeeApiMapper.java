package com.invex.employee.api.mapper;

import com.invex.employee.api.dto.EmployeeRequestDTO;
import com.invex.employee.api.dto.EmployeeResponseDTO;
import com.invex.employee.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeApiMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Employee toDomain(EmployeeRequestDTO dto);

    EmployeeResponseDTO toResponse(Employee domain);

    List<EmployeeResponseDTO> toResponseList(List<Employee> domains);

    List<Employee> toDomainList(List<EmployeeRequestDTO> dtos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateDomainFromDto(EmployeeRequestDTO dto, @MappingTarget Employee domain);
}
