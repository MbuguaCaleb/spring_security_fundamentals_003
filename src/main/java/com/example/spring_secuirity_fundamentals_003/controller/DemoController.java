package com.example.spring_secuirity_fundamentals_003.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo(){
       return "Caleb Masters Spring Security";
    }
}
