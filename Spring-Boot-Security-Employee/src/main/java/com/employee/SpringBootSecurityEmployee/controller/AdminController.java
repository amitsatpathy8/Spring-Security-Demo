package com.employee.SpringBootSecurityEmployee.controller;

import com.employee.SpringBootSecurityEmployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    EmployeeService service;

    @DeleteMapping("/deleteEmployee/{email}")
    public String deleteEmployee(@PathVariable String email) {
        return service.deleteEmployee(email);
    }
}
