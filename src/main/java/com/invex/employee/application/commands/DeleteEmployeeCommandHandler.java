package com.invex.employee.application.commands;

import com.invex.employee.api.exception.EmployeeNotFoundException;
import com.invex.employee.domain.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteEmployeeCommandHandler {

    private final EmployeeRepository repository;

    @Transactional
    public void handle(Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new EmployeeNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
