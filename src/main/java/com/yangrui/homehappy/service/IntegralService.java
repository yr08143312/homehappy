package com.yangrui.homehappy.service;

import com.yangrui.homehappy.vo.DateIntegral;
import com.yangrui.homehappy.vo.ScoreVo;

public interface IntegralService {
    DateIntegral getDateIntegral(String date);

    int getTotalIntegral();

    boolean addIntegral(int integral) throws Exception;

    ScoreVo getStatistics();
}
