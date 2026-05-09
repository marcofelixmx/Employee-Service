package com.invex.employee.application.commands;

import com.invex.employee.domain.model.Employee;
import com.invex.employee.domain.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateEmployeesCommandHandler {

    private final EmployeeRepository repository;

    @Transactional
    public List<Employee> handle(List<Employee> employees) {
        return repository.saveAll(employees);
    }
}
