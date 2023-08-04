package com.employee.SpringBootSecurityEmployee.repo;

import com.employee.SpringBootSecurityEmployee.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    public List<Employee> findByEname(String name);
    public Employee findByEmail(String email);
}
