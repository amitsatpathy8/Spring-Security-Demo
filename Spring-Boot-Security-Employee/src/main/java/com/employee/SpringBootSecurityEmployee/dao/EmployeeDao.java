package com.employee.SpringBootSecurityEmployee.dao;

import com.employee.SpringBootSecurityEmployee.dto.Employee;
import com.employee.SpringBootSecurityEmployee.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDao {
    @Autowired
    EmployeeRepo repo;

    public boolean saveEmployee(Employee employee) {
        Employee employee1 = repo.findByEmail(employee.getEmail());
        if (employee1 == null) {
            repo.save(employee);
            return true;
        }
        return false;
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee getEmployeeByEmail(String email) {
        return repo.findByEmail(email);
    }

    public List<Employee> getEmployeeByName(String name) {
        return repo.findByEname(name);
    }

    public boolean deleteEmployee(int id) {
        Optional<Employee> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return true;
        }
        return false;
    }
}
