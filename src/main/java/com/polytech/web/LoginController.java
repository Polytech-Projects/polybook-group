package com.polytech.web;

import com.polytech.services.FeedService;
import com.polytech.services.LoginService;
import com.polytech.services.PublicationService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller du profil
 */
@Controller
public class LoginController {
    private LoginService loginService;                        // Service de lecture de la base de donnee

    public LoginController(LoginService loginService) {
        this.loginService = loginService ;
    }

    /**
     * Page de login personnalisé
     *
     * @return page du login
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    /**
     * Page de register personnalisé
     *
     * @return page du register
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "register";
    }

    /**
     * Rajout d'un utilisateur ( register )
     */
    @RequestMapping(value = "/register/addUser", method = RequestMethod.POST)
    public String addUser(String username, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        loginService.addUser(username, hashedPassword) ;
        return "redirect:/login" ;
    }
}
