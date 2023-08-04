package com.employee.SpringBootSecurityEmployee.SecurityConfig;

import com.employee.SpringBootSecurityEmployee.dto.Employee;
import com.employee.SpringBootSecurityEmployee.dto.EmployeeCredencialsWithRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class EmployeeIntoUserDetails implements UserDetails {

    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    public EmployeeIntoUserDetails(EmployeeCredencialsWithRole employee) {
        this.email = employee.getEmail();
        this.password = employee.getPassword();
        this.authorities = employee.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
