package com.example.admin.controller;

import com.example.library.dto.AdminDto;
import com.example.library.model.Admin;
import com.example.library.service.AdminService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String loginForm(Model model) {
        model.addAttribute("title","Login");
        return "login";
    }
    @GetMapping("/index")
    public String home(Model model){
        model.addAttribute("title","HomePage");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "redirect:/login";
        }
        return "index";
    }
    @GetMapping("register")
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
            , Model model) {
        try{
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
                model.addAttribute("EmailError","Your email has been register");
                return "register";
            }
            if(adminDto.getPassword().equals(adminDto.getPasswordRepeat())){
                adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                adminService.save(adminDto);
                model.addAttribute("adminDto", adminDto);
                model.addAttribute("success","Register Success !");
            }
            else{
                model.addAttribute("adminDto",adminDto);
                System.out.println("Password is not same !");
                model.addAttribute("PasswordError","Password is not same!");
                return "register";
            }

        }catch(Exception exception){
            exception.printStackTrace();
            model.addAttribute("errors", "The server has error !");
        }
        return "register";
    }
}
