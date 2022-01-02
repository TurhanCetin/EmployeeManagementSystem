package com.example.employemanagementsystem.Controller;


import com.example.employemanagementsystem.Model.Department;
import com.example.employemanagementsystem.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService service;

    @GetMapping("/dashboard/department")
    public String getDashboardDepartment(Model model){
        List<Department> listDepartment = service.listAll();
        model.addAttribute("listDepartment", listDepartment);
        return "Dashboard/departmentDashboard";
    }

    @PostMapping("/department/create")
    public String createDepartment(@ModelAttribute Department department ,RedirectAttributes redirectAttribute){
        service.saveDepartment(department);
        redirectAttribute.addFlashAttribute("message", "Department Was Created Successfully");
        return "redirect:/dashboard/department";
    }
    @RequestMapping("/dashboard/department/delete/{id}")
    public String deleteRole(@PathVariable Integer id , RedirectAttributes redirectAttributes ){
        service.deleteDepartment(id);
        redirectAttributes.addFlashAttribute("message", "Department Was Deleted Successfully");
        return "redirect:/dashboard/department";
    }
}
