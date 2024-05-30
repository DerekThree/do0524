package com.tool_rental.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agreement {

    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private LocalDate checkOutDate;
    private LocalDate dueDate;
    private int dailyRentalCharge;
    private int chargeDays;
    private int preDiscountCharge;
    private int discountPercent;
    private int discountAmount;
    private int finalCharge;

    public void print() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String output = "Tool code: " + toolCode + "\n" +
                "Tool type: " + toolType + "\n" +
                "Tool Brand: " + toolBrand + "\n" +
                "Rental days: " + rentalDays + "\n" +
                "Check out date: " + checkOutDate.format(formatter) + "\n" +
                "Due date: " + dueDate.format(formatter) + "\n" +
                "Rental charge: $%.2f\n".formatted((float) dailyRentalCharge/100) +
                "Charge days: " + chargeDays + "\n" +
                "Pre-discount charge: $%.2f\n".formatted((float) preDiscountCharge/100) +
                "Discount percent: " + discountPercent + "%\n" +
                "Discount amount: $%.2f\n".formatted((float) discountAmount/100) +
                "Final charge: $%.2f\n".formatted((float) finalCharge/100);

        System.out.println(output);
    }
}
