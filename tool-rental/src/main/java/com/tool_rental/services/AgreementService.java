package com.tool_rental.services;

import com.tool_rental.models.Agreement;
import com.tool_rental.models.AgreementRequest;
import org.springframework.http.ResponseEntity;

public interface AgreementService {
    public ResponseEntity<Agreement> getRentalAgreement(AgreementRequest request);
}
