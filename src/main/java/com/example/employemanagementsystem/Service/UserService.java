package com.example.employemanagementsystem.Service;


import com.example.employemanagementsystem.Model.Department;
import com.example.employemanagementsystem.Model.Role;
import com.example.employemanagementsystem.Model.User;
import com.example.employemanagementsystem.Repository.DepartmentRepository;
import com.example.employemanagementsystem.Repository.RoleRepository;
import com.example.employemanagementsystem.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UsersRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    DepartmentRepository depRepo;

    public void saveUserWithDefaultRole(User user){
        user.setPassword(user.getPassword());

        Role roleUser = roleRepo.findByName("User");
        user.addRole(roleUser);

        userRepo.save(user);
    }

    public List<User> listAll(){
        return userRepo.findAll();
    }
    public User get(Integer id){
        return userRepo.findById(id).get();
    }
    public List<Role> getRoles(){
        return roleRepo.findAll();
    }
    public List<Department> getDep(){
        return depRepo.findAll();
    }
    public void edit(User user){
        user.setPassword(user.getPassword());
        userRepo.save(user);
    }
}
