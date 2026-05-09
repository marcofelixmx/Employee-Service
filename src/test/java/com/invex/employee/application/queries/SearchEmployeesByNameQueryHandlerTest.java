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
class SearchEmployeesByNameQueryHandlerTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private SearchEmployeesByNameQueryHandler handler;

    @Test
    void shouldReturnEmployeesMatchingName() {
        // Given
        String name = "John";
        List<Employee> employees = List.of(
                Employee.builder().id(1L).firstName("John").build()
        );
        when(repository.findByFirstNameContainingIgnoreCase(name)).thenReturn(employees);

        // When
        List<Employee> result = handler.handle(name);

        // Then
        assertThat(result).hasSize(1).isEqualTo(employees);
        verify(repository).findByFirstNameContainingIgnoreCase(name);
    }

    @Test
    void shouldReturnEmptyListWhenNoEmployeesMatchName() {
        // Given
        String name = "Unknown";
        when(repository.findByFirstNameContainingIgnoreCase(name)).thenReturn(List.of());

        // When
        List<Employee> result = handler.handle(name);

        // Then
        assertThat(result).isEmpty();
        verify(repository).findByFirstNameContainingIgnoreCase(name);
    }
}
