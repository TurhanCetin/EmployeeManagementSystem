package com.example.employemanagementsystem.Controller;


import com.example.employemanagementsystem.Repository.RoleRepository;
import com.example.employemanagementsystem.Repository.UsersRepository;
import com.example.employemanagementsystem.Service.UserService;
import com.example.employemanagementsystem.Form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.employemanagementsystem.Model.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;


@Controller
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;


    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@ModelAttribute (name="loginForm") LoginForm loginForm, HttpSession session, HttpServletRequest request , HttpServletResponse response) {
        User user = userRepo.findUserByEmail(loginForm.getEmail());
       String role = user.getRoles().stream().
               map(n -> String.valueOf(n))
               .collect(Collectors.joining(""));
        if (user != null && user.getPassword().equals(loginForm.getPassword())){
            if (role.equals("User")){
                Cookie cookie = new Cookie("email", loginForm.getEmail());
                cookie.setMaxAge(60*60*24);
                response.addCookie(cookie);
                Cookie  cookie1 = new Cookie("password",loginForm.getPassword());
                response.addCookie(cookie1);
                session.setAttribute("loginUser",loginForm.getEmail());
                Cookie[] cookies = request.getCookies();
                return "redirect:/user/home";
            }else if(role.equals("Manager")){
                Cookie cookie = new Cookie("email", loginForm.getEmail());
                cookie.setMaxAge(60*60*24);
                response.addCookie(cookie);
                Cookie  cookie1 = new Cookie("password",loginForm.getPassword());
                response.addCookie(cookie1);
                session.setAttribute("loginManager",loginForm.getEmail());
                Cookie[] cookies = request.getCookies();
                return "Pages/personnelHome";
            }
        }
        return "redirect:/personnel/login";
    }

    @RequestMapping(value ="/dashboardLogin", method = RequestMethod.POST)
    public String adminLogin(@ModelAttribute (name="loginForm") LoginForm loginForm, HttpSession session, Model model, HttpServletRequest request , HttpServletResponse response){
        User user = userRepo.findUserByEmail(loginForm.getEmail());
        String role = user.getRoles().stream().
                map(n -> String.valueOf(n))
                .collect(Collectors.joining(""));
        if (user != null && user.getPassword().equals(loginForm.getPassword())){
            if (role.equals("Admin")){
                Cookie cookie = new Cookie("email", loginForm.getEmail());
                cookie.setMaxAge(60*60*24);
                response.addCookie(cookie);
                Cookie  cookie1 = new Cookie("password",loginForm.getPassword());
                response.addCookie(cookie1);
                session.setAttribute("loginAdmin",loginForm.getEmail());
                Cookie[] cookies = request.getCookies();
                return "redirect:/dashboard/home";
            }
        }
        return "redirect:/dashboard/login";
    }

    @PostMapping(value = "/personnel/save")
    public String processRegistration(@ModelAttribute User user){

        service.saveUserWithDefaultRole(user);
        return "Pages/personnelLogin";
    }


    @PostMapping("/personnel/edit")
    public String editPersonnel(User user, RedirectAttributes redirectAttributes){
        service.edit(user);
        redirectAttributes.addFlashAttribute("message" , "Personnel was edit successfully");
        return "redirect:/dashboard/home";
    }


}
