package com.example.admin.controller;

import com.example.library.dto.AdminDto;
import com.example.library.model.Admin;
import com.example.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        return "forgot-password";
    }

    // su dung Model de nhan du lieu tu Controller de hien thi tren html
    // @ModelAttribute : su dung doi tuong adminDto de nhan doi tuong tren Html
    @PostMapping("/register-new")
    public String registerNew (@ModelAttribute("adminDto") AdminDto adminDto
            , BindingResult result
            , Model model
            ,HttpSession section) {
        try{

            section.removeAttribute("message");
            if(result.hasErrors()){
                model.addAttribute("adminDto",adminDto);
                result.toString();
                return "register";
            }
            String username = adminDto.getUserName();
            Admin admin = adminService.findByUserName(username);
            if(admin != null){
                model.addAttribute("adminDto",adminDto);
                System.out.println("Your email has been register");
                section.setAttribute("message","Your email has been register");
                return "register";
            }
            if(adminDto.getPassword().equals(adminDto.getPasswordRepeat())){
                adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                adminService.save(adminDto);
                model.addAttribute("adminDto", adminDto);
                section.setAttribute("message","Register successfully! ");
            }
            else{
                model.addAttribute("adminDto",adminDto);
                System.out.println("Password is not same !");
                section.setAttribute("message","Password is not same !");
                return "register";
            }

        }catch(Exception exception){
            section.setAttribute("message","Can not register !");
            exception.printStackTrace();
        }
        return "register";
    }
}
