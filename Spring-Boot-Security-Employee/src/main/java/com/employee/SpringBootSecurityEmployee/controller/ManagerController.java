package com.employee.SpringBootSecurityEmployee.controller;

import com.employee.SpringBootSecurityEmployee.dto.Employee;
import com.employee.SpringBootSecurityEmployee.service.EmployeeService;
import com.employee.SpringBootSecurityEmployee.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    ManagerService service;

    @PostMapping("/save")
    public boolean saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/addRole/{email}/{role}")
    public String addRole(@PathVariable String email, @PathVariable String role) {
        return service.addNewRole(email, role);
    }
}
