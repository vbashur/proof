package com.vbashur.actuator.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationWebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/1")
    public String index1() {
        return "index1";
    }


}
