package com.example.Employeemngt.controller;

import com.example.Employeemngt.entity.Employee;
import com.example.Employeemngt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id, Locale locale) {
        return employeeService.getEmployeeById(id)
                .orElse(null); // Return null if employee is not found
    }

    @PostMapping
    public String createEmployee(@RequestBody Employee employee, Locale locale) {
        String message = employeeService.createEmployee(employee);
        return messageSource.getMessage(message, null, locale);
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails, Locale locale) {
        String message = employeeService.updateEmployee(id, employeeDetails);
        return messageSource.getMessage(message, null, locale);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id, Locale locale) {
        employeeService.deleteEmployee(id);
        return messageSource.getMessage("employee.deleted", null, locale);
    }
}
