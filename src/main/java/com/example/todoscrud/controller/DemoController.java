package com.example.todoscrud.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class DemoController {

    @GetMapping("/")
    public String ok() {
        return "app is working.";
    }
}
