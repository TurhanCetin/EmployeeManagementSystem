package com.example.employemanagementsystem.Controller;


import com.example.employemanagementsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.employemanagementsystem.Model.User;



@Controller
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/personnel/save")
    public String processRegistration(User user){
        service.saveUserWithDefaultRole(user);
        return "Pages/personnelLogin";
    }
}
