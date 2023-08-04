package com.employee.SpringBootSecurityEmployee.SecurityConfig;

import com.employee.SpringBootSecurityEmployee.dao.EmployeeDao;
import com.employee.SpringBootSecurityEmployee.dao.RolesDao;
import com.employee.SpringBootSecurityEmployee.dto.Employee;
import com.employee.SpringBootSecurityEmployee.dto.EmployeeCredencialsWithRole;
import com.employee.SpringBootSecurityEmployee.dto.Roles;
import com.employee.SpringBootSecurityEmployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeIntoUserDetailsService implements UserDetailsService {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    RolesDao rolesDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeDao.getEmployeeByEmail(email);
        if (employee != null) {
            List<Roles> roles = rolesDao.getRolesByEmail(email);
            List<GrantedAuthority> rolesOfEmployee = new ArrayList<>();

            EmployeeCredencialsWithRole credencialsWithRole = new EmployeeCredencialsWithRole();

            for (Roles role : roles) {
                rolesOfEmployee.add(new SimpleGrantedAuthority(role.getRole()));
            }

            credencialsWithRole.setEmail(employee.getEmail());
            credencialsWithRole.setPassword(employee.getEpassword());
            credencialsWithRole.setRoles(rolesOfEmployee);
            return new EmployeeIntoUserDetails(credencialsWithRole);
        } else {
            throw new UsernameNotFoundException("Employee Not Present with email: "+email);
        }
    }
}
