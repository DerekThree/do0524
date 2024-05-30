package com.tool_rental.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class AgreementRequest {
    private String toolCode;
    private int dayCount;
    private int discountPercent;
    private LocalDate checkOutDate;

    @Override
    public String toString() {
        return "RentalAgreementRequest {" +
                "toolCode='" + toolCode + '\'' +
                ", dayCount=" + dayCount +
                ", discountPercent=" + discountPercent +
                ", checkOutDate=" + checkOutDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")) +
                '}';
    }
}
