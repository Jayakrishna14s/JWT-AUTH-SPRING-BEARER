package com.marketanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketanalysis.dto.LoginDTO;
import com.marketanalysis.dto.SignupDTO;
import com.marketanalysis.service.jwt.JwtService;
import com.marketanalysis.service.jwt.UserInfoService;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired 
    private UserInfoService service;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupDTO payload) throws Exception {
        System.out.println("Initiating the user creation");
        return service.addUser(payload);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO payload){
        System.out.println(payload.getUsername()+ " " + payload.getPassword());
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(payload.getUsername(), payload.getPassword())
        );
        System.out.println("Yoyoyoyoy");
        if (authentication.isAuthenticated()) {
            System.out.println("Authenticated");
            return jwtService.generateToken(payload.getUsername());
        } else {
            System.out.println("Unauthenticated");
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    

}
