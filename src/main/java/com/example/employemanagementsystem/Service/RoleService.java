package com.example.employemanagementsystem.Service;


import com.example.employemanagementsystem.Model.Role;
import com.example.employemanagementsystem.Model.User;
import com.example.employemanagementsystem.Repository.RoleRepository;
import com.example.employemanagementsystem.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepo;

    public List<Role> listAll(){
        return roleRepo.findAll();
    }

    public void saveRole(Role role){
        roleRepo.save(role);
    }
    public void deleteRole(Integer id){
        roleRepo.deleteById(id);

    }
}
