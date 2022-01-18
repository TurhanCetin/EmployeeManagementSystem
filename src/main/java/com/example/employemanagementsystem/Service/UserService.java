package com.example.employemanagementsystem.Service;


import com.example.employemanagementsystem.Model.Department;
import com.example.employemanagementsystem.Model.Role;
import com.example.employemanagementsystem.Model.User;
import com.example.employemanagementsystem.Repository.DepartmentRepository;
import com.example.employemanagementsystem.Repository.RoleRepository;
import com.example.employemanagementsystem.Repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Slf4j
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UsersRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    DepartmentRepository depRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void saveUserWithDefaultRole(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepo.findByEmail(email);
        if (user==null){
            log.error("User Not Founded!");
            throw  new UsernameNotFoundException("User Not Founded!");
        }else {
            log.info("User {} is Founded",email);
        }
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
