package com.invex.employee.application.queries;

import com.invex.employee.domain.model.Employee;
import com.invex.employee.domain.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllEmployeesQueryHandlerTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private GetAllEmployeesQueryHandler handler;

    @Test
    void shouldReturnAllEmployees() {
        // Given
        List<Employee> employees = List.of(
                Employee.builder().id(1L).firstName("John").build(),
                Employee.builder().id(2L).firstName("Jane").build()
        );
        when(repository.findAll()).thenReturn(employees);

        // When
        List<Employee> result = handler.handle();

        // Then
        assertThat(result).hasSize(2).isEqualTo(employees);
        verify(repository).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoEmployeesFound() {
        // Given
        when(repository.findAll()).thenReturn(List.of());

        // When
        List<Employee> result = handler.handle();

        // Then
        assertThat(result).isEmpty();
        verify(repository).findAll();
    }
}
