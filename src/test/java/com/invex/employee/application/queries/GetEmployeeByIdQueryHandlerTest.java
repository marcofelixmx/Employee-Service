package com.invex.employee.application.queries;

import com.invex.employee.api.exception.EmployeeNotFoundException;
import com.invex.employee.domain.model.Employee;
import com.invex.employee.domain.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetEmployeeByIdQueryHandlerTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private GetEmployeeByIdQueryHandler handler;

    @Test
    void shouldReturnEmployeeWhenExists() {
        // Given
        Long id = 1L;
        Employee employee = Employee.builder().id(id).firstName("John").build();
        when(repository.findById(id)).thenReturn(Optional.of(employee));

        // When
        Employee result = handler.handle(id);

        // Then
        assertThat(result).isEqualTo(employee);
        verify(repository).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenEmployeeDoesNotExist() {
        // Given
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> handler.handle(id))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessageContaining("Employee not found with ID: " + id);

        verify(repository).findById(id);
    }
}
