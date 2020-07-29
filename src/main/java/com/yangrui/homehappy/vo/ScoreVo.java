package com.yangrui.homehappy.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class ScoreVo {
    private int weekTotal;
    private double weekAverage;//周平均分
    private int weekAwardFar;
    private int weekSettlementDateFar;//距本周结算日还差几天

    private int total;
    private double totalAverage;//总平均分
    private int firstAwardFar;
    private int secondAwardFar;
    private int thirdAwardFar;
    private int ultimateAwardFar;
    private String settlementDate;//总分结算日
    private int settlementDateFar;//距总分结算日还差几天

}
