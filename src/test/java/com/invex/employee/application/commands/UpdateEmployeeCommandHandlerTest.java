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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateEmployeeCommandHandlerTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private UpdateEmployeeCommandHandler handler;

    @Test
    void shouldUpdateEmployeeSuccessfully() {
        // Given
        Long id = 1L;
        Employee existing = Employee.builder().id(id).firstName("Old").build();
        Employee updateData = Employee.builder().firstName("New").build();
        
        when(repository.findById(id)).thenReturn(Optional.of(existing));
        when(repository.save(any(Employee.class))).thenAnswer(i -> i.getArguments()[0]);

        // When
        Employee result = handler.handle(id, updateData);

        // Then
        assertThat(result.getFirstName()).isEqualTo("New");
        verify(repository).save(any(Employee.class));
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {
        // Given
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> handler.handle(id, new Employee()))
                .isInstanceOf(EmployeeNotFoundException.class);
    }
}
