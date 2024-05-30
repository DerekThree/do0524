package com.tool_rental.services;

import com.tool_rental.models.Agreement;
import com.tool_rental.models.AgreementRequest;
import com.tool_rental.repositories.ToolChargesRepository;
import com.tool_rental.repositories.ToolRepository;
import com.tool_rental.shared.TestUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementServiceImplTest {

    @InjectMocks
    AgreementServiceImpl rentalAgreementService;
    @Mock
    ToolRepository toolRepository;
    @Mock
    ToolChargesRepository toolChargesRepository;
    @Mock
    ChargeDaysServiceImpl chargeDaysService;

    @Test
    void getRentalAgreementTest() {

        when(toolRepository.findByCode(anyString()))
                .thenAnswer(i -> new TestUtility().getMockToolMap().get(i.getArguments()[0]));
        when(toolChargesRepository.findByType(anyString()))
                .thenAnswer(i -> new TestUtility().getMockToolChargesMap().get(i.getArguments()[0]));

        List<Agreement> mockList = new TestUtility().getMockRAList();
        for (Agreement mock : mockList) {
            AgreementRequest request = new AgreementRequest(
                    mock.getToolCode(),
                    mock.getRentalDays(),
                    mock.getDiscountPercent(),
                    mock.getCheckOutDate());
            when(chargeDaysService.getChargeDays(any(LocalDate.class), anyInt(), anyBoolean(), anyBoolean()))
                    .thenReturn(mock.getChargeDays());
            assertEquals(mock, rentalAgreementService.getRentalAgreement(request).getBody(),
                    "Failed for the expected RentalAgreement");
        }
    }
}
