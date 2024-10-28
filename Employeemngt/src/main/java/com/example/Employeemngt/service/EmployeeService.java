package com.example.Employeemngt.service;

import com.example.Employeemngt.entity.Employee;
import com.example.Employeemngt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MessageSource messageSource;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public String createEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "employee.added";
    }

    public String updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("employee.not.found")
        );
        employee.setName(employeeDetails.getName());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setSalary(employeeDetails.getSalary());
        employeeRepository.save(employee);
        return "employee.added";
    }

    public String deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("employee.not.found");
        }
        employeeRepository.deleteById(id);
        return "employee.deleted";
    }
}