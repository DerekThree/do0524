package com.tool_rental.services;

import com.tool_rental.models.Agreement;
import com.tool_rental.models.AgreementRequest;
import com.tool_rental.models.Tool;
import com.tool_rental.models.ToolCharges;
import com.tool_rental.repositories.ToolRepository;
import com.tool_rental.repositories.ToolChargesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AgreementServiceImpl implements AgreementService {

    @Autowired
    private ToolRepository toolRepository;
    @Autowired
    private ToolChargesRepository toolChargesRepository;
    @Autowired
    private ChargeDaysService chargeDaysService;

    public ResponseEntity<Agreement> getRentalAgreement(AgreementRequest request) {
        Tool tool = toolRepository.findByCode(request.getToolCode());
        ToolCharges toolCharges = toolChargesRepository.findByType(tool.getType());
        int chargeDays = chargeDaysService.getChargeDays(
                request.getCheckOutDate(),
                request.getDayCount(),
                toolCharges.isWeekendCharge(),
                toolCharges.isHolidayCharge());
        Agreement agreement = new Agreement();
        agreement.setChargeDays(chargeDays);
        agreement.setToolCode(tool.getCode());
        agreement.setToolType(tool.getType());
        agreement.setToolBrand(tool.getBrand());
        agreement.setRentalDays(request.getDayCount());
        agreement.setCheckOutDate(request.getCheckOutDate());
        agreement.setDueDate(request.getCheckOutDate().plusDays(request.getDayCount()));
        agreement.setDailyRentalCharge(toolCharges.getDailyCharge());
        agreement.setPreDiscountCharge(agreement.getChargeDays() * agreement.getDailyRentalCharge());
        agreement.setDiscountPercent(request.getDiscountPercent());
        float discountAmount = (float) agreement.getDiscountPercent() / 100 * agreement.getPreDiscountCharge();
        agreement.setDiscountAmount(Math.round(discountAmount));
        agreement.setFinalCharge(agreement.getPreDiscountCharge() - agreement.getDiscountAmount());

        return new ResponseEntity<>(agreement, HttpStatus.OK);
    }
}
