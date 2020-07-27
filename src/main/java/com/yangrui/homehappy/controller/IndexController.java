package com.yangrui.homehappy.controller;

import com.yangrui.homehappy.service.IntegralService;
import com.yangrui.homehappy.vo.ResultDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class IndexController {

    @Resource
    private IntegralService integralService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("total", integralService.getTotalIntegral());
        model.addAttribute("today",integralService.getDateIntegral(""));
        return "index";
    }

    @RequestMapping(value="index/add",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO addIntegral(HttpServletRequest request){
        ResultDTO result = new ResultDTO();
        try {
            int integral = Integer.parseInt(request.getParameter("integral"));
            integralService.addIntegral(integral);
            result.setData(integral);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultDTO.STATUS_CODE_BUSINESS_ERROR);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("index/timetable")
    public String timetable(){
        return "timetable";
    }


}
