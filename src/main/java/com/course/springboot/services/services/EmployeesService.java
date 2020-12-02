package com.course.springboot.services.services;

import com.course.springboot.services.config.error.RestException;
import com.course.springboot.services.vo.Employee;

import java.util.List;

public interface EmployeesService {

    List<Employee> getEmployees(String name);

    Employee getEmployee(int id);

    void createEmployee(Employee employee) throws RestException;

    void updateEmployee(int id, Employee employee) throws RestException;

    void deleteEmployee(int id) throws RestException;
}
