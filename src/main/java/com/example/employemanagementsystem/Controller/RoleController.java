package com.example.employemanagementsystem.Controller;

import com.example.employemanagementsystem.Model.Role;
import com.example.employemanagementsystem.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    RoleService service;

    @GetMapping("/dashboard/role")
    public String getDashboardRole(Model model){
        List<Role> listRole = service.listAll();
        model.addAttribute("listRole", listRole);
        return "Dashboard/roleDashboard";
    }
}
