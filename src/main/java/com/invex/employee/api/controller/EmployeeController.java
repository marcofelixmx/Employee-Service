package com.invex.employee.api.controller;

import com.invex.employee.api.dto.EmployeeRequestDTO;
import com.invex.employee.api.dto.EmployeeResponseDTO;
import com.invex.employee.api.mapper.EmployeeApiMapper;
import com.invex.employee.application.commands.CreateEmployeesCommandHandler;
import com.invex.employee.application.commands.DeleteEmployeeCommandHandler;
import com.invex.employee.application.commands.UpdateEmployeeCommandHandler;
import com.invex.employee.application.queries.GetAllEmployeesQueryHandler;
import com.invex.employee.application.queries.GetEmployeeByIdQueryHandler;
import com.invex.employee.application.queries.SearchEmployeesByNameQueryHandler;
import com.invex.employee.domain.model.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Validated
@Tag(name = "Employee Management", description = "Operations for managing employees")
public class EmployeeController {

    private final CreateEmployeesCommandHandler createHandler;
    private final UpdateEmployeeCommandHandler updateHandler;
    private final DeleteEmployeeCommandHandler deleteHandler;
    private final GetAllEmployeesQueryHandler getAllHandler;
    private final GetEmployeeByIdQueryHandler getByIdHandler;
    private final SearchEmployeesByNameQueryHandler searchHandler;
    private final EmployeeApiMapper mapper;

    @GetMapping
    @Operation(summary = "Get all employees")
    public ResponseEntity<List<EmployeeResponseDTO>> getAll() {
        List<Employee> employees = getAllHandler.handle();
        return ResponseEntity.ok(mapper.toResponseList(employees));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID")
    public ResponseEntity<EmployeeResponseDTO> getById(@PathVariable Long id) {
        Employee employee = getByIdHandler.handle(id);
        return ResponseEntity.ok(mapper.toResponse(employee));
    }

    @PostMapping
    @Operation(summary = "Create one or multiple employees")
    public ResponseEntity<List<EmployeeResponseDTO>> create(@RequestBody @Valid List<EmployeeRequestDTO> requests) {
        List<Employee> domains = mapper.toDomainList(requests);
        List<Employee> saved = createHandler.handle(domains);
        return new ResponseEntity<>(mapper.toResponseList(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an employee")
    public ResponseEntity<EmployeeResponseDTO> update(@PathVariable Long id, @RequestBody @Valid EmployeeRequestDTO request) {
        Employee updateData = mapper.toDomain(request);
        Employee updated = updateHandler.handle(id, updateData);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deleteHandler.handle(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Search employees by name (partial match)")
    public ResponseEntity<List<EmployeeResponseDTO>> search(@RequestParam String name) {
        List<Employee> results = searchHandler.handle(name);
        return ResponseEntity.ok(mapper.toResponseList(results));
    }
}
