package com.westerndigital.keyinsight.Login;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.westerndigital.keyinsight.JiraUser.JiraUser;
import com.westerndigital.keyinsight.Login.LoginService;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        // inject the service layer
        this.loginService = loginService; 
    }
    
    @PostMapping("/login")
    public ResponseEntity<JiraUser> login(@RequestBody JiraUser user) {
        // process POST request


        return ResponseEntity.ok(user);
    }

}
