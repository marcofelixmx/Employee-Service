package com.invex.employee.application.queries;

import com.invex.employee.domain.model.Employee;
import com.invex.employee.domain.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchEmployeesByNameQueryHandler {

    private final EmployeeRepository repository;

    public List<Employee> handle(String name) {
        return repository.findByFirstNameContainingIgnoreCase(name);
    }
}
