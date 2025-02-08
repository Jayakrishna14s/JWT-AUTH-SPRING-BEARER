package com.marketanalysis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @GetMapping("/check")
    public String check() {
        System.out.println("I am user");
        return "I am user";
    }
}
 