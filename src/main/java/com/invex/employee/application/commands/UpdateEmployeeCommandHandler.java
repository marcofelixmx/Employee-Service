package com.invex.employee.application.commands;

import com.invex.employee.api.exception.EmployeeNotFoundException;
import com.invex.employee.domain.model.Employee;
import com.invex.employee.domain.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateEmployeeCommandHandler {

    private final EmployeeRepository repository;

    @Transactional
    public Employee handle(Long id, Employee updatedEmployeeData) {
        Employee existingEmployee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        
        existingEmployee.setFirstName(updatedEmployeeData.getFirstName());
        existingEmployee.setSecondName(updatedEmployeeData.getSecondName());
        existingEmployee.setPaternalSurname(updatedEmployeeData.getPaternalSurname());
        existingEmployee.setMaternalSurname(updatedEmployeeData.getMaternalSurname());
        existingEmployee.setSex(updatedEmployeeData.getSex());
        existingEmployee.setBirthDate(updatedEmployeeData.getBirthDate());
        existingEmployee.setPosition(updatedEmployeeData.getPosition());
        existingEmployee.setActive(updatedEmployeeData.getActive());

        return repository.save(existingEmployee);
    }
}
