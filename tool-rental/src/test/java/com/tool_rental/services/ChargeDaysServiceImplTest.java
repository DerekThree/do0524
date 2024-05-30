package com.tool_rental.services;

import com.tool_rental.models.Agreement;
import com.tool_rental.models.ToolCharges;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import com.tool_rental.shared.TestUtility;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ChargeDaysServiceImplTest {

    @InjectMocks
    ChargeDaysServiceImpl chargeDaysService;

    @Test
    void getChargeDaysTest() {
        List<Agreement> mockList = new TestUtility().getMockRAList();
        for (Agreement mock : mockList) {
            ToolCharges charges = new TestUtility().getMockToolChargesMap().get(mock.getToolType());
            int chargeDays = chargeDaysService.getChargeDays(mock.getCheckOutDate(), mock.getRentalDays(),
                    charges.isWeekendCharge(), charges.isHolidayCharge());
            assertEquals(mock.getChargeDays(), chargeDays,
                    "Incorrect charge days for " + mock);
        }
    }
}
