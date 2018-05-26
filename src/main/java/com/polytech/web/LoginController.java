package com.polytech.web;

import com.polytech.services.FeedService;
import com.polytech.services.LoginService;
import com.polytech.services.PublicationService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    private LoginService loginService;                        // Service de lecture de la base de donnee

    public LoginController(LoginService loginService) {
        this.loginService = loginService ;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "register";
    }

    @RequestMapping(value = "/register/addUser", method = RequestMethod.POST)
    public String addUser(String username, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        loginService.addUser(username, hashedPassword) ;
        return "redirect:/login" ;
    }
}
