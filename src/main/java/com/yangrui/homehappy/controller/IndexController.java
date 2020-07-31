package com.yangrui.homehappy.controller;

import com.yangrui.homehappy.service.IntegralService;
import com.yangrui.homehappy.utils.DateTimeUtil;
import com.yangrui.homehappy.vo.ResultDTO;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${week.award:63}")
    private int weekAward;

    @GetMapping("")
    public String index(Model model){
        setTitle(model);
        //星期一需要展示得到奖励与否
        if(DateTimeUtil.getDayOfTheWeek() == 1) {
            int lastWeekIntegral = integralService.getLastWeekIntegral();
            if(lastWeekIntegral >= weekAward){
                model.addAttribute("lastWeekAward", "恭喜，上周"+lastWeekIntegral+"分,获取周奖励！请继续保持。");
                model.addAttribute("color", "red");
            }else{
                model.addAttribute("lastWeekAward", "很遗憾,上周"+lastWeekIntegral+"分,这周要努力哦。");
                model.addAttribute("color", "blue");
            }


        }
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
    public String timetable(Model model) {
        setTitle(model);
        return "timetable";
    }

    @GetMapping("index/queryClassroom")
    public String queryClassroom(Model model){
        setTitle(model);
        return "query_classroom";
    }

    @GetMapping("index/queryScore")
    public String queryScore(Model model){
        setTitle(model);
        model.addAttribute("score", integralService.getStatistics());
        return "query_score";
    }

    public void setTitle(Model model){
        model.addAttribute("total", integralService.getTotalIntegral());
        model.addAttribute("today",integralService.getDateIntegral(""));
    }
}
