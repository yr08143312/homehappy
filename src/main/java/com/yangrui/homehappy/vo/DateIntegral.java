package com.yangrui.homehappy.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@Data
@ToString
public class DateIntegral {
    private Date date;
    private int integral;
    private Date operTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateIntegral that = (DateIntegral) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
