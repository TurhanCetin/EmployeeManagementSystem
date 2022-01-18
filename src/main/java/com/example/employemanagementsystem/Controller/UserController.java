package com.example.employemanagementsystem.Controller;


import com.example.employemanagementsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import com.example.employemanagementsystem.Model.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping(value = "/personnel/save")
    public String processRegistration(@ModelAttribute User user){
        service.saveUserWithDefaultRole(user);
        return "Pages/personnelLogin";
    }

    @RequestMapping("/personnel/login")
    public String getPersonnelLogin(Model model ,User user ){
        model.addAttribute("user" , user);
        return "Pages/personnelHome";
    }

    @PostMapping("/personnel/edit")
    public String editPersonnel(User user, RedirectAttributes redirectAttributes){
        service.edit(user);
        redirectAttributes.addFlashAttribute("message" , "Personnel was edit successfully");
        return "redirect:/dashboard/home";
    }


}
