package com.yangrui.homehappy.dao;

import com.yangrui.homehappy.vo.DateIntegral;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface IntegralMapper {

    @Select("select * from t_date_integral where date = date_format(#{_parameter},'%Y-%m-%d')")
    DateIntegral getDateIntegral(String date);

    @Select("SELECT IFNULL(SUM(integral),0) FROM t_date_integral")
    int getTotalIntegral();

    @Insert("insert into t_date_integral(date,integral) values (#{date},#{integral})")
    int addIntegral(@Param("date") Date date, @Param("integral")int integral);

    @Select("select * from t_date_integral")
    List<DateIntegral> getIntegralDatas();

    @Select("SELECT IFNULL(SUM(integral),0) FROM t_date_integral where date BETWEEN #{start} and #{end}")
    int getRangeDateTotalIntegral(@Param("start")Date start, @Param("end")Date end);
}
