package com.employee.SpringBootSecurityEmployee.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
public class EmployeeCredencialsWithRole {
    private String email;
    private String password;
    private List<GrantedAuthority> roles;

}
