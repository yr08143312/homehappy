package com.yangrui.homehappy.controller;

import com.yangrui.homehappy.service.IntegralService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value="add",method = RequestMethod.POST)
    public String addIntegral(HttpServletRequest request,Model model){
        try {
            int integral = Integer.parseInt(request.getParameter("integral"));
            integralService.addIntegral(integral);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
        }
        model.addAttribute("total", integralService.getTotalIntegral());
        model.addAttribute("today",integralService.getDateIntegral(""));
        return "index";
    }
}
