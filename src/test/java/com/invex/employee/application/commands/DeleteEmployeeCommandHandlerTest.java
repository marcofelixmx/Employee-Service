package com.invex.employee.application.commands;

import com.invex.employee.api.exception.EmployeeNotFoundException;
import com.invex.employee.domain.model.Employee;
import com.invex.employee.domain.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteEmployeeCommandHandlerTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private DeleteEmployeeCommandHandler handler;

    @Test
    void shouldDeleteEmployeeWhenExists() {
        // Given
        Long id = 1L;
        Employee employee = Employee.builder().id(id).build();
        when(repository.findById(id)).thenReturn(Optional.of(employee));

        // When
        handler.handle(id);

        // Then
        verify(repository).findById(id);
        verify(repository).deleteById(id);
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
        verify(repository, never()).deleteById(anyLong());
    }
}
