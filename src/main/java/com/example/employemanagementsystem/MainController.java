package com.example.employemanagementsystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String getHome(){
        return "home";
    }
    @GetMapping("/personnel/login")
    public String getPersonnelLogin(){
        return "Pages/personnelLogin";
    }

    @GetMapping("/personnel/register")
    public String getPersonnelRegister(){
        return "Pages/personnelRegister";
    }

    @GetMapping("/dashboard/login")
    public String getManagerLogin(){
        return "Dashboard/managerLogin";
    }
}
