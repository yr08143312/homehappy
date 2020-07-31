package com.yangrui.homehappy.component;

import com.yangrui.homehappy.service.IntegralService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TaskService {

    @Resource
    private IntegralService integralService;

    @Resource
    private EmailService emailService;

    @Value("${week.award:63}")
    private int weekAward;

    @Value("${spring.mail.to}")
    private String mailTo;

    @Scheduled(cron = "0 0 9 ? * 1")
    public void awardWeekMessage(){
        int lastWeekIntegral = integralService.getLastWeekIntegral();
        if(lastWeekIntegral >= weekAward){
            emailService.sendAttachmentMail("卓卓获得奖励","恭喜卓卓，上周得到"+lastWeekIntegral+"分,获取周奖励！请继续保持。",mailTo.split("\\|"));
        }
    }
}
