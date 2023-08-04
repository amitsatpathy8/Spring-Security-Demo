package com.employee.SpringBootSecurityEmployee.service;

import com.employee.SpringBootSecurityEmployee.dao.EmployeeDao;
import com.employee.SpringBootSecurityEmployee.dao.RolesDao;
import com.employee.SpringBootSecurityEmployee.dto.Employee;
import com.employee.SpringBootSecurityEmployee.dto.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManagerService {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    RolesDao rolesDao;

    public String addNewRole(String email, String role) {
        Employee employee = employeeDao.getEmployeeByEmail(email);
        role = "ROLE_"+role;
        if (employee != null) {
            List<Roles> roles = rolesDao.getRolesByEmail(email);
            boolean isPresent = false;
            for (Roles value : roles) {
                if (value.getRole().equals(role)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Roles roles1 = new Roles();
                roles1.setRole(role);
                roles1.setEmail(email);
                rolesDao.saveRole(roles1);
                return "Role added successfully";
            }
            return "Role is already assigned";
        } else {
            return "Invalid Email : " + email + " User does not exist";
        }
    }

}
