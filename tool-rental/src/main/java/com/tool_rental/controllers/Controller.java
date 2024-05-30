package com.tool_rental.controllers;

import com.tool_rental.models.Agreement;
import com.tool_rental.models.AgreementRequest;
import com.tool_rental.services.AgreementService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@Validated
public class Controller {

    @Autowired
    AgreementService agreementService;

    @RequestMapping("/v1/rentalAgreement")
    public ResponseEntity<Agreement> getResponse(@RequestParam String toolCode,
                                                 @RequestParam @Min(1) int dayCount,
                                                 @RequestParam @Range(min = 0, max = 100) int discountPercent,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        var request = new AgreementRequest(toolCode, dayCount, discountPercent, date);
        return agreementService.getRentalAgreement(request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleException(ConstraintViolationException ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("Request validation error: ");
        for (var violation : ex.getConstraintViolations()) {
            String paramName = violation.getPropertyPath().toString().split("\\.")[1];
            sb.append(paramName).append(" ").append(violation.getMessage()).append(". ");
        }
        sb.append("Please update request parameter(s).");
        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }
}