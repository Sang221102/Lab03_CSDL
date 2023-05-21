package com.example.demo.controller;

import com.example.demo.DemoApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class Homecontroller {
    @GetMapping("/")
    public String home() {
        return "home/index";
    }
}
