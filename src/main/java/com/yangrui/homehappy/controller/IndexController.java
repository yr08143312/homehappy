package com.yangrui.homehappy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/hello")
    public String testHelloHtml(Model model){
        model.addAttribute("hello", " 你好 ");
        return "index";
    }
}
