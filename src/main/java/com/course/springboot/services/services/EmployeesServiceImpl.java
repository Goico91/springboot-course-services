package com.course.springboot.services.services;

import com.course.springboot.services.commons.enums.RestExceptionE;
import com.course.springboot.services.config.error.RestException;
import com.course.springboot.services.vo.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for Employees
 */
@Service
public class EmployeesServiceImpl implements EmployeesService{

    private final List<Employee> employees = new ArrayList<>();

    public EmployeesServiceImpl() {
        employees.add(new Employee(0, "Adams", "Usman", "12345678", 20, "intern", Arrays.asList("java", "spring", "node")));
        employees.add(new Employee(1, "Baker", "Hills", "87654321", 22, "intern", Arrays.asList("angular", "react", "ionic")));
        employees.add(new Employee(2, "Clark", "Irwin", "18273645", 26, "developer", Arrays.asList("java", "spring", "spring boot")));
        employees.add(new Employee(3, "Davis", "Jones", "54637181", 30, "developer", Arrays.asList("react", "ionic")));
        employees.add(new Employee(4, "Evans", "Klein", "19283745", 34, "senior developer", Arrays.asList("java", "spring boot")));
        employees.add(new Employee(5, "Frank", "Mason", "19827432", 38, "development project leader", Arrays.asList("java", "spring", "spring boot", "angular")));
    }

    @Override
    public List<Employee> getEmployees(String name) {
        List<Employee> employeesAux = employees;

        if(name != null && !"".equals(name)) {
            employeesAux = employees.stream().filter(employee -> name.equals(employee.getName())).collect(Collectors.toList());
        }

        return employeesAux;
    }

    @Override
    public Employee getEmployee(int id) {
        return employees.stream().filter(employee -> employee.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void createEmployee(Employee newEmployee) throws RestException {
        Employee employeeDB = employees.stream().filter(employee -> newEmployee.getName().equals(employee.getName()) && newEmployee.getSurname().equals(employee.getSurname())).findFirst().orElse(null);

        if (employeeDB != null) {
            throw new RestException(RestExceptionE.ERROR_EMPLOYEE_ALREADY_EXISTS);
        }

        int id = 0;
        long skip = employees.isEmpty() ? 0 : employees.size()-1L;
        Optional<Employee> lastEmployee = employees.stream().skip(skip).findFirst();
        if(lastEmployee.isPresent()) {
            id = lastEmployee.get().getId() + 1;
        }

        newEmployee.setId(id);
        employees.add(newEmployee);
    }

    @Override
    public void updateEmployee(int id, Employee employeeUp) throws RestException {
        Employee employeeDB = this.getEmployee(id);
        if (employeeDB == null) {
            throw new RestException(RestExceptionE.ERROR_EMPLOYEE_NOT_FOUND);
        }

        // Update employee
        employeeDB.setName(employeeUp.getName());
        employeeDB.setSurname(employeeUp.getSurname());

        if(employeeUp.getAge() != null)
            employeeDB.setAge(employeeUp.getAge());

        if(employeeUp.getRol() != null)
            employeeDB.setRol(employeeUp.getRol());
    }

    @Override
    public void deleteEmployee(int id) throws RestException {
        Employee employeeDB = this.getEmployee(id);
        if (employeeDB == null) {
            throw new RestException(RestExceptionE.ERROR_EMPLOYEE_NOT_FOUND);
        }

        employees.removeIf(employee -> employee.getId() == id);
    }
}
