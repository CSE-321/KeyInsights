package com.westerndigital.keyinsight.Login;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.westerndigital.keyinsight.User.User;

import com.westerndigital.keyinsight.Login.LoginService;

@RestController
public class LoginController {

    // @Autowired
    // private LoginService loginService;
    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        // process POST request

        String email = user.getEmail();
        String serverUrl = user.getServerUrl();

        System.out.println("Email: " + email + ", Server URL: " + serverUrl);

        System.out.println(ResponseEntity.ok(user));

        // loginService.getLogin();


        
        return ResponseEntity.ok(user);
    }
    
}
