package com.tool_rental.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tool_charges")
public class ToolCharges {
    @Id
    @Column(name = "type")
    private String type;
    @Column(name = "daily_charge")
    private int dailyCharge;
    @Column(name = "weekend_charge")
    private boolean weekendCharge;
    @Column(name = "holiday_charge")
    private boolean holidayCharge;
}
