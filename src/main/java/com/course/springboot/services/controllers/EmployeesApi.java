package com.course.springboot.services.controllers;

import com.course.springboot.services.controllers.dto.EmployeeDTO;
import com.course.springboot.services.controllers.dto.ListEmployeesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * API for Employees Services
 */
@RequestMapping(value = "/employees")
public interface EmployeesApi {

    @GetMapping
    ResponseEntity<List<ListEmployeesDTO>> getEmployees(@RequestParam(required = false) String name);

    @GetMapping("/{id}")
    ResponseEntity<EmployeeDTO> getEmployee(@PathVariable int id);

    @PostMapping
    ResponseEntity<Void> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO);

    @PutMapping("/{id}")
    ResponseEntity<Void> updateEmployee(@PathVariable int id, @Valid @RequestBody EmployeeDTO employeeDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteEmployee(@PathVariable int id);
}
