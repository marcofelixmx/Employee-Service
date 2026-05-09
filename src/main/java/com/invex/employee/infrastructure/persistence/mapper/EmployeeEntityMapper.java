package com.invex.employee.infrastructure.persistence.mapper;

import com.invex.employee.domain.model.Employee;
import com.invex.employee.infrastructure.persistence.entity.EmployeeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeEntityMapper {
    Employee toDomain(EmployeeEntity entity);
    EmployeeEntity toEntity(Employee domain);
    List<Employee> toDomainList(List<EmployeeEntity> entities);
    List<EmployeeEntity> toEntityList(List<Employee> domains);
}
