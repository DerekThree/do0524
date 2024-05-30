package com.tool_rental.services;

import java.time.LocalDate;

public interface ChargeDaysService {
    int getChargeDays(LocalDate checkOutDate, int dayCount, boolean chargeWeekends, boolean ChargeHolidays);
}
