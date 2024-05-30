package com.tool_rental.repositories;

import com.tool_rental.models.ToolCharges;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolChargesRepository extends JpaRepository<ToolCharges, String> {
    public ToolCharges findByType(String type);
}
