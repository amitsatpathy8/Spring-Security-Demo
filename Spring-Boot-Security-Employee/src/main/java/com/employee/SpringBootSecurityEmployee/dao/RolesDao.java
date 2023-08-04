package com.employee.SpringBootSecurityEmployee.dao;

import com.employee.SpringBootSecurityEmployee.dto.Roles;
import com.employee.SpringBootSecurityEmployee.repo.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RolesDao {
    @Autowired
    RolesRepo repo;

    public void saveRole(Roles roles){
        repo.save(roles);
    }

    public List<Roles> getRolesByEmail(String  email){
        return repo.findByEmail(email);
    }

    public void deleteRole(int id){
        Optional<Roles> byId = repo.findById(id);
        if(byId.isPresent()){
            repo.delete(byId.get());
        }
    }


}
