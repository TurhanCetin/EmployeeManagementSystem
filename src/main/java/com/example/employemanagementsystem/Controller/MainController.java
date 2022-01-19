package com.example.employemanagementsystem.Controller;


import com.example.employemanagementsystem.Model.Department;
import com.example.employemanagementsystem.Model.Role;
import com.example.employemanagementsystem.Model.User;
import com.example.employemanagementsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    public UserService service;

    @GetMapping("")
    public String getHome(){
        return "home";
    }

    @RequestMapping(value = "/personnel/home")
    public String personnelHome(Model model, HttpSession session){
    if(session.getAttribute("loginUser") != null){
        return "Pages/personnelPage";
    }
    model.addAttribute("LoginForm");
    return "redirect:/personnel/login";
    }
    @RequestMapping(value = "/user/home")
    public String userHome(Model model, HttpSession session){
    if(session.getAttribute("loginUser") != null){
        return "Pages/userHome";
    }
    model.addAttribute("LoginForm");
    return "redirect:/personnel/login";
    }

    @RequestMapping(value = "/personnel/login",method = RequestMethod.GET)
    public String getPersonnelLogin(Model model, HttpSession session, Role role){
        if(session.getAttribute("loginUser") != null){
            return "userHome";
        }
        List<Role> listRoles = service.getRoles();
        model.addAttribute("listRoles",listRoles);
        model.addAttribute("LoginForm");
        return "Pages/personnelLogin";
    }

    @GetMapping("/personnel/home")
    public String getPersonnelHome(){

        return "Pages/personnelHome";
    }

    @GetMapping("/personnel/register")
    public String getPersonnelRegister(Model model){
        model.addAttribute("user", new User());
        return "Pages/personnelRegister";
    }

    @RequestMapping(value = "/dashboard/login",method=RequestMethod.GET)
    public String getManagerLogin(Model model, HttpSession session){
        if(session.getAttribute("loginAdmin") != null){
            return "Dashboard/adminDashboard";
        }
        model.addAttribute("LoginForm");
        return "Dashboard/login";
    }

    @RequestMapping(value="/dashboard/home",method = RequestMethod.GET)
    public String getDashboardHome(Model model, HttpSession session){
        if(session.getAttribute("loginAdmin") != null){
            List<User> listUsers = service.listAll();
            model.addAttribute("listUser", listUsers);
            return "Dashboard/adminDashboard";
        }
       model.addAttribute("LoginForm");
        return "redirect:/dashboard/login";
    }

    @GetMapping("/dashboard/personnel/edit/{id}")
    public String personnelEdit(@PathVariable("id") Integer id, Model model ){

        User user = service.get(id);
        List<Role> listRoles = service.getRoles();
        List<Department> listDep = service.getDep();

        model.addAttribute("user",user);
        model.addAttribute("listRoles",listRoles);
        model.addAttribute("listDep", listDep);


        return "Dashboard/editPersonnelPage";
    }

    @GetMapping("/dashboard/department/create")
    public String getDepartmentCreate(Model model){
        model.addAttribute("department", new Department());
        return "Dashboard/createDepartmentPage";
    }

    @GetMapping("/dashboard/role/create")
    public String getRoleCreate(Model model){
        model.addAttribute("role", new Role());
        return "Dashboard/createRolePage";
    }

    @GetMapping("/403")
    public String invalidRequest(){

        return "/Pages/403";
    }



}
