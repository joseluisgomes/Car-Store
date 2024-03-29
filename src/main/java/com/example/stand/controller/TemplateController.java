package com.example.stand.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {
    @GetMapping("login")
    public String getLoginView() { return "login"; }

    @GetMapping("data")
    public String getDataView() { return "data"; }

    @GetMapping("index")
    public String getIndexView() { return "index"; }
}
