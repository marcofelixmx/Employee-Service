package com.invex.employee.infrastructure.persistence.repository;

import com.invex.employee.domain.model.Employee;
import com.invex.employee.domain.repository.EmployeeRepository;
import com.invex.employee.infrastructure.persistence.entity.EmployeeEntity;
import com.invex.employee.infrastructure.persistence.mapper.EmployeeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JpaEmployeeRepository jpaRepository;
    private final EmployeeEntityMapper mapper;

    @Override
    public List<Employee> findAll() {
        return mapper.toDomainList(jpaRepository.findAll());
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity entity = mapper.toEntity(employee);
        EmployeeEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<Employee> saveAll(List<Employee> employees) {
        List<EmployeeEntity> entities = mapper.toEntityList(employees);
        List<EmployeeEntity> savedEntities = jpaRepository.saveAll(entities);
        return mapper.toDomainList(savedEntities);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByFirstNameContainingIgnoreCase(String name) {
        return mapper.toDomainList(jpaRepository.findByFirstNameContainingIgnoreCase(name));
    }
}
