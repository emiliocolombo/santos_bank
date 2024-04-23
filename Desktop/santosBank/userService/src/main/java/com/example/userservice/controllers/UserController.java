package com.example.userservice.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class UserController {

    @GetMapping("hello")
    @PreAuthorize("permitAll()")
    public String hello(){
        return "hello world";
    }

    @GetMapping("helloSecured")
    @PreAuthorize("hasAuthority('READ')")
    public String helloSecured(){
        return "hello world Secured";
    }
}