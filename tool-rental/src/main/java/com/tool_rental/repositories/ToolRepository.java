package com.tool_rental.repositories;

import com.tool_rental.models.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolRepository extends JpaRepository<Tool, String> {
    public Tool findByCode(String code);
}
