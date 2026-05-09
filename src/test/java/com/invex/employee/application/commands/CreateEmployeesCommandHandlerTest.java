package com.invex.employee.application.commands;

import com.invex.employee.domain.model.Employee;
import com.invex.employee.domain.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateEmployeesCommandHandlerTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private CreateEmployeesCommandHandler handler;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .firstName("John")
                .paternalSurname("Doe")
                .position("Developer")
                .active(true)
                .build();
    }

    @Test
    void shouldSaveAllEmployees() {
        // Given
        List<Employee> employees = List.of(employee);
        when(repository.saveAll(anyList())).thenReturn(employees);

        // When
        List<Employee> result = handler.handle(employees);

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFirstName()).isEqualTo("John");
        verify(repository).saveAll(employees);
    }
}
