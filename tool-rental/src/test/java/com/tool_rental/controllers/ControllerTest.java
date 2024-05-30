package com.tool_rental.controllers;

import com.tool_rental.models.Agreement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import com.tool_rental.shared.TestUtility;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class ControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void controllerTest() throws Exception {
        String url = "http://localhost:" + port + "/v1/rentalAgreement";

        List<Agreement> mockList = new TestUtility().getMockRAList();
        for (Agreement mock : mockList) {
            String params = "?toolCode=%s&dayCount=%d&discountPercent=%d&date=%s"
                    .formatted(mock.getToolCode(),
                            mock.getRentalDays(),
                            mock.getDiscountPercent(),
                            mock.getCheckOutDate());
            var response = this.restTemplate.getForEntity(url + params, String.class);
            if (mock.getRentalDays() < 1 ||
                    mock.getDiscountPercent() > 100 ||
                    mock.getDiscountPercent() < 0) {
                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(),
                        "Incorrect response code for " + mock);
            }
            else {
                assertEquals(HttpStatus.OK, response.getStatusCode(),
                        "Incorrect response code for " + mock);
            }
        }
    }
}
