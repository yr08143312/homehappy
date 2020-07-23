package com.yangrui.homehappy.service.impl;

import com.yangrui.homehappy.dao.IntegralMapper;
import com.yangrui.homehappy.service.IntegralService;
import com.yangrui.homehappy.utils.DateTimeUtil;
import com.yangrui.homehappy.vo.DateIntegral;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalTime;

@Service
public class IntegralServiceImpl implements IntegralService {

    @Resource
    private IntegralMapper integralMapper;

    @Value("${times.lot.start:21}")
    private int timesLotStart;

    @Value("${times.lot.end:23}")
    private int timesLotEnd;

    @Override
    public DateIntegral getDateIntegral(String date) {
        if(StringUtils.isEmpty(date)){
            date = DateTimeUtil.getNowDate8String();
        }
        return integralMapper.getDateIntegral(date);
    }

    @Override
    public int getTotalIntegral() {
        return integralMapper.getTotalIntegral();
    }

    @Override
    public boolean addIntegral(int integral) throws Exception {
        LocalTime time = LocalTime.now();
        if(time.getHour() < timesLotStart || time.getHour() > timesLotEnd){
            throw new Exception("每天打分时间段为"+timesLotStart+"：00-"+timesLotEnd+":00");
        }
        return integralMapper.addIntegral(DateTimeUtil.getNowDate(),integral) == 1;
    }
}
