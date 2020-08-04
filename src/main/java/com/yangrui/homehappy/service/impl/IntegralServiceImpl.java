package com.yangrui.homehappy.service.impl;

import com.yangrui.homehappy.dao.IntegralMapper;
import com.yangrui.homehappy.service.IntegralService;
import com.yangrui.homehappy.utils.DateTimeUtil;
import com.yangrui.homehappy.vo.DateIntegral;
import com.yangrui.homehappy.vo.ScoreVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntegralServiceImpl implements IntegralService {

    @Resource
    private IntegralMapper integralMapper;

    @Value("${times.lot.start:21}")
    private int timesLotStart;

    @Value("${times.lot.end:23}")
    private int timesLotEnd;

    @Value("${week.award:63}")
    private int weekAward;

    @Value("${first.award:272}")
    private int firstAward;

    @Value("${second.award:311}")
    private int secondAward;

    @Value("${third.award:351}")
    private int thirdAward;

    @Value("${ultimate.award:381}")
    private int ultimateAward;

    @Value("${final.settlement.date:2020-08-30}")
    private String finalSettlementDate;

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

    @Override
    public ScoreVo getStatistics(){
        ScoreVo vo = new ScoreVo();
        List<DateIntegral> integrals = integralMapper.getIntegralDatas();
        Date startDayOfWeek = DateTimeUtil.getStartDayOfWeek();
        Date endDayOfWeek = DateTimeUtil.getEndDayOfWeek();
        List<DateIntegral> weekIntegrals = integrals.stream().filter(x ->
                (x.getDate().compareTo(startDayOfWeek) >=0 && x.getDate().compareTo(endDayOfWeek) <=0)).collect(Collectors.toList()
        );
        //设置周数据
        weekIntegrals.forEach(x -> vo.setWeekTotal(vo.getWeekTotal() + x.getIntegral()));
        vo.setWeekAverage(new BigDecimal(vo.getWeekTotal()).divide(new BigDecimal(weekIntegrals.size()),2,BigDecimal.ROUND_HALF_UP).doubleValue());
        vo.setWeekAwardFar(weekAward-vo.getWeekTotal());
        vo.setWeekSettlementDateFar(8 - DateTimeUtil.getNowDayOfWeek());

        //设置总数据
        integrals.forEach(x -> vo.setTotal(vo.getTotal() + x.getIntegral()));
        vo.setTotalAverage(new BigDecimal(vo.getTotal()).divide(new BigDecimal(integrals.size()),2,BigDecimal.ROUND_HALF_UP).doubleValue());
        vo.setFirstAwardFar(firstAward > vo.getTotal() ? firstAward - vo.getTotal() : 0);
        vo.setSecondAwardFar(secondAward > vo.getTotal() ? secondAward - vo.getTotal() : 0);
        vo.setThirdAwardFar(thirdAward > vo.getTotal() ? thirdAward - vo.getTotal() : 0);
        vo.setUltimateAwardFar(ultimateAward > vo.getTotal() ? ultimateAward - vo.getTotal() : 0);
        vo.setSettlementDate(finalSettlementDate.replaceFirst("-","年").replaceFirst("-","月") + "日");
        vo.setSettlementDateFar(DateTimeUtil.getDateFar(finalSettlementDate));

        return vo;
    }

    @Override
    public int getLastWeekIntegral() {
        Date lastWeekMonday = DateTimeUtil.agoWeekByLocalDateTime(DayOfWeek.MONDAY,1);
        Date lastWeekSunday = DateTimeUtil.agoWeekByLocalDateTime(DayOfWeek.MONDAY,0);
        return integralMapper.getRangeDateTotalIntegral(lastWeekMonday,lastWeekSunday);
    }
}
