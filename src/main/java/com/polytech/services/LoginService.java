package com.polytech.services;

import com.polytech.persistence.LoginRepository;

public class LoginService {
    private LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository ;
    }

    public void addUser(String userName, String password) {
        loginRepository.addUser(userName, password) ;
    }
}
