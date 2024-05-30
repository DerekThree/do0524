package com.tool_rental.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tool_data")
public class Tool {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "type")
    private String type;
    @Column(name = "brand")
    private String brand;
}
