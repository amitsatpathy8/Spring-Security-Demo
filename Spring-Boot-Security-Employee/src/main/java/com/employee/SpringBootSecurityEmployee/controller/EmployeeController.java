package com.employee.SpringBootSecurityEmployee.controller;

import com.employee.SpringBootSecurityEmployee.dto.Employee;
import com.employee.SpringBootSecurityEmployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployees(){
        return service.getAllEmployee();
    }

    @GetMapping("/getByName/{name}")
    public List<Employee> getByName(@PathVariable String name){
        return service.searchEmployeeByName(name);
    }

}
