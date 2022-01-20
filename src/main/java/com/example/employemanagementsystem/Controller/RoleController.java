package com.example.employemanagementsystem.Controller;

import com.example.employemanagementsystem.Model.Role;
import com.example.employemanagementsystem.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/role/create")
    public String createRole(@ModelAttribute Role role, RedirectAttributes redirectAttributes){
        service.saveRole(role);
        redirectAttributes.addFlashAttribute("message", "New Role Was Created Successfuly");
        return "redirect:/dashboard/role";
    }

    @PostMapping("/role/edit")
    public String editRole(Role role){
        service.edit(role);
        return "redirect:/dashboard/role";
    }

    @RequestMapping("/dashboard/role/delete/{id}")
    public String deleteRole(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        service.deleteRole(id);
        redirectAttributes.addFlashAttribute("message" , "Role was deleted successfuly");
        return "redirect:/dashboard/role";
    }
}
