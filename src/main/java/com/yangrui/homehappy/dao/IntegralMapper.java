package com.yangrui.homehappy.dao;

import com.yangrui.homehappy.vo.DateIntegral;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface IntegralMapper {

    @Select("select * from t_date_integral where date = date_format(#{_parameter},'%Y-%m-%d')")
    DateIntegral getDateIntegral(String date);

    @Select("select sum(integral) from t_date_integral")
    int getTotalIntegral();

    @Insert("insert into t_date_integral(date,integral) values (#{date},#{integral})")
    int addIntegral(Date date, int integral);
}
