package com.employee.SpringBootSecurityEmployee.repo;

import com.employee.SpringBootSecurityEmployee.dto.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesRepo extends JpaRepository<Roles,Integer> {
    public List<Roles> findByEmail(String email);
}
