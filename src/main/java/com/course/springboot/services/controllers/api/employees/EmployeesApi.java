package com.course.springboot.services.controllers.api.employees;

import com.course.springboot.services.commons.ConstantsUrl;
import com.course.springboot.services.config.error.RestException;
import com.course.springboot.services.controllers.dto.EmployeeDTO;
import com.course.springboot.services.controllers.dto.ListEmployeesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * API for Employees Services
 */
@RequestMapping(value = ConstantsUrl.API_EMPLOYEES)
public interface EmployeesApi {

    @GetMapping
    ResponseEntity<List<ListEmployeesDTO>> getEmployees(@RequestParam(required = false) String name);

    @GetMapping(ConstantsUrl.ID)
    ResponseEntity<EmployeeDTO> getEmployee(@PathVariable int id);

    @PostMapping
    ResponseEntity<Void> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws RestException;

    @PutMapping(ConstantsUrl.ID)
    ResponseEntity<Void> updateEmployee(@PathVariable int id, @Valid @RequestBody EmployeeDTO employeeDTO) throws RestException;

    @DeleteMapping(ConstantsUrl.ID)
    ResponseEntity<Void> deleteEmployee(@PathVariable int id) throws RestException;
}
