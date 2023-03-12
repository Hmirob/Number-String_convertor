package com.shmyrov.testtask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactController {
    @GetMapping("/morda/**")
    public String getIndex() {
        return "/index.html";
    }
}
