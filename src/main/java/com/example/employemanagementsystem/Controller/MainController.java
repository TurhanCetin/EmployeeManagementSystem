package com.example.employemanagementsystem.Controller;

import com.example.employemanagementsystem.Model.Role;
import com.example.employemanagementsystem.Model.User;
import com.example.employemanagementsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    public UserService service;

    @GetMapping("")
    public String getHome(){
        return "home";
    }
    @GetMapping("/personnel/login")
    public String getPersonnelLogin(){
        return "Pages/personnelLogin";
    }

    @GetMapping("/personnel/register")
    public String getPersonnelRegister(Model model){
        model.addAttribute("user", new User());
        return "Pages/personnelRegister";
    }

    @GetMapping("/dashboard/login")
    public String getManagerLogin(){
        return "Dashboard/login";
    }

    @GetMapping("/dashboard/home")
    public String getDashboardHome(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUser", listUsers);
        return "Dashboard/adminDashboard";
    }

}
