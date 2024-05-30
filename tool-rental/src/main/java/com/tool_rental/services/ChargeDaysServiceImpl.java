package com.tool_rental.services;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

@Service
public class ChargeDaysServiceImpl implements ChargeDaysService{

    public int getChargeDays(LocalDate checkOutDate, int dayCount, boolean chargeWeekends, boolean chargeHolidays) {
        int chargeDays = 0;
        for (int day = 0; day < dayCount; day++) {
            LocalDate date = checkOutDate.plusDays(day);
            if ((chargeWeekends || !isOnWeekend(date)) &&
                    (chargeHolidays || !isHoliday(date))) chargeDays++;
        }
        return chargeDays;
    }

    private boolean isOnWeekend(LocalDate date) {
        String dayOfWeek = date.getDayOfWeek().name();
        return (dayOfWeek.equals("SATURDAY") || dayOfWeek.equals("SUNDAY"));
    }

    private boolean isHoliday(LocalDate date) {
        return isIndependenceDay(date) || isLaborDay(date);
    }

    private boolean isIndependenceDay(LocalDate date) {
        LocalDate observed;
        String actualDayOfWeek = date.withMonth(7).withDayOfMonth(4).getDayOfWeek().name();

        if (actualDayOfWeek.equals("SATURDAY")) observed = date.withMonth(7).withDayOfMonth(3);
        else if (actualDayOfWeek.equals("SUNDAY")) observed = date.withMonth(7).withDayOfMonth(5);
        else observed = date.withMonth(7).withDayOfMonth(4);

        return date.isEqual(observed);
    }

    private boolean isLaborDay(LocalDate date) {
        return date.getMonth() == Month.SEPTEMBER &&
                date.getDayOfMonth() <= 7 &&
                date.getDayOfWeek() == DayOfWeek.MONDAY;

    }
}
