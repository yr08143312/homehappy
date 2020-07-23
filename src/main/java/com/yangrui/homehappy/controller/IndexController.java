package com.yangrui.homehappy.controller;

import com.yangrui.homehappy.service.IntegralService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Resource
    private IntegralService integralService;

    @RequestMapping("")
    public String index(Model model){
        model.addAttribute("total", integralService.getTotalIntegral());
        model.addAttribute("today",integralService.getDateIntegral(""));
        return "index";
    }
}
