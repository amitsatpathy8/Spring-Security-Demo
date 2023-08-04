package com.employee.SpringBootSecurityEmployee.service;

import com.employee.SpringBootSecurityEmployee.dao.EmployeeDao;
import com.employee.SpringBootSecurityEmployee.dao.RolesDao;
import com.employee.SpringBootSecurityEmployee.dto.Employee;
import com.employee.SpringBootSecurityEmployee.dto.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeService {
    @Autowired
    EmployeeDao dao;

    @Autowired
    RolesDao rolesDao;

    public boolean saveEmployee(Employee employee) {
        employee.setEpassword(new BCryptPasswordEncoder(16).encode(employee.getEpassword()));
        System.out.println(employee.getEpassword());
        if (dao.saveEmployee(employee)) {
            Roles role = new Roles();
            role.setEmail(employee.getEmail());
            role.setRole("ROLE_EMPLOYEE");
            rolesDao.saveRole(role);
            return true;
        }
        return false;
    }

    public List<Employee> getAllEmployee() {
        return dao.getAllEmployees();
    }

    public List<Employee> searchEmployeeByName(String name) {
        return dao.getEmployeeByName(name);
    }

    public String deleteEmployee(String email) {
        Employee employee = dao.getEmployeeByEmail(email);
        if (employee != null) {
            if (dao.deleteEmployee(employee.getEid())) {
                List<Roles> roles= rolesDao.getRolesByEmail(email);
                for(Roles role : roles){
                    rolesDao.deleteRole(role.getId());
                }
                return "Successfully deleted the employee having email : " + email;
            }
            return "Something went Wrong :( ";
        } else
            return "Invalid Email ID";
    }
}
