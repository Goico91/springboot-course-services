package com.course.springboot.services.controllers;

import com.course.springboot.services.controllers.dto.EmployeeDTO;
import com.course.springboot.services.controllers.dto.ListEmployeesDTO;
import com.course.springboot.services.controllers.mappers.EmployeeToEmployeeDTOMapper;
import com.course.springboot.services.controllers.mappers.EmployeeToListEmployeesDTOMapper;
import com.course.springboot.services.vo.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ControllerImpl for Employees Services
 */
@RestController
public class EmployeesController implements EmployeesApi {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeesController() {
        employees.add(new Employee(0, "Adams", "Usman", "12345678", 20, "intern", Arrays.asList("java", "spring", "spring boot", "angular")));
        employees.add(new Employee(1, "Baker", "Hills", "87654321", 22, "intern", Arrays.asList("angular", "react", "spring boot")));
        employees.add(new Employee(2, "Clark", "Irwin", "18273645", 26, "developer", Arrays.asList("java", "spring", "spring boot")));
        employees.add(new Employee(3, "Davis", "Jones", "54637181", 30, "developer", Arrays.asList("react", "ionic")));
        employees.add(new Employee(4, "Evans", "Klein", "19283745", 34, "senior developer", Arrays.asList("java", "spring", "spring boot")));
        employees.add(new Employee(5, "Frank", "Mason", "19827432", 38, "development project leader", Arrays.asList("java", "spring", "spring boot", "angular")));
    }

    @Override
    public ResponseEntity<List<ListEmployeesDTO>> getEmployees(String name) {
        List<Employee> employeesAux = employees;
        if(name != null && !"".equals(name)) {
            employeesAux = employees.stream().filter(employee -> name.equals(employee.getName())).collect(Collectors.toList());
        }
        return ResponseEntity.ok().body(EmployeeToListEmployeesDTOMapper.INSTANCE.employeeToListEmployeesDTO(employeesAux));
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployee(int id) {
        // Search for employee
        Employee employeeAux = employees.stream().filter(employee -> employee.getId() == id).findFirst().orElse(null);

        // Check if null
        if (employeeAux == null) {
            return ResponseEntity.notFound().build();
        }

        // Employee to EmployeeDTO
        EmployeeDTO employeeDTO = EmployeeToEmployeeDTOMapper.INSTANCE.employeeToEmployeeDTO(employeeAux);
        return ResponseEntity.ok().body(employeeDTO);
    }

    @Override
    public ResponseEntity<Void> createEmployee(EmployeeDTO employeeDTO) {
        // Check if exists
        Employee employeeAux = employees.stream().filter(employee -> employee.getName().equals(employeeDTO.getName())
                && employee.getSurname().equals(employeeDTO.getSurname())).findFirst().orElse(null);

        if (employeeAux != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Map EmployeeDTO to Employee
        Employee employee = EmployeeToEmployeeDTOMapper.INSTANCE.employeeDTOToEmployee(employeeDTO);
        int id = employees.stream().skip(employees.size()-1).findFirst().get().getId() + 1;
        employee.setId(id);
        employees.add(employee);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateEmployee(int id, @Valid EmployeeDTO employeeDTO) {
        Employee employeeAux = employees.stream().filter(employee -> employee.getId() == id).findFirst().orElse(null);
        if (employeeAux == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Map EmployeeDTO to Employee
        Employee employeeUpdate = EmployeeToEmployeeDTOMapper.INSTANCE.employeeDTOToEmployee(employeeDTO);
        employeeAux.setName(employeeUpdate.getName());
        employeeAux.setSurname(employeeUpdate.getSurname());

        if(employeeAux.getAge() != null) {
            employeeAux.setAge(employeeUpdate.getAge());
        }

        if(employeeAux.getRol() != null) {
            employeeAux.setRol(employeeUpdate.getRol());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(int id) {
        Employee employeeAux = employees.stream().filter(employee -> employee.getId() == id).findFirst().orElse(null);
        if (employeeAux == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employees.removeIf(employee -> employee.getId() == id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
