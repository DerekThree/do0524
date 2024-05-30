package com.tool_rental.shared;

import com.tool_rental.models.Agreement;
import com.tool_rental.models.AgreementRequest;
import com.tool_rental.models.Tool;
import com.tool_rental.models.ToolCharges;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class TestUtility {

    private final List<Agreement> MockRAList = new ArrayList<>();
    private final Map<String, Tool> mockToolMap = new HashMap<>();
    private final Map<String, ToolCharges> mockToolChargesMap = new HashMap<>();

    public TestUtility() {
        addMock(new Tool("CHNS", "Chainsaw", "Stihl"));
        addMock(new Tool("LADW", "Ladder", "Werner"));
        addMock(new Tool("JAKD", "Jackhammer", "DeWalt"));
        addMock(new Tool("JAKR", "Jackhammer", "Ridgid"));
        addMock(new ToolCharges("Ladder", 199, true, false));
        addMock(new ToolCharges("Chainsaw", 149, false, true));
        addMock(new ToolCharges("Jackhammer", 299, false, false));

        addMock(new AgreementRequest("JAKR", 5, 101, parseCheckoutDate("09/03/15")),
                2);
        addMock(new AgreementRequest("LADW", 3, 10, parseCheckoutDate("07/02/20")),
                2);
        addMock(new AgreementRequest("CHNS", 5, 25, parseCheckoutDate("07/02/15")),
                3);
        addMock(new AgreementRequest("JAKD", 6, 0, parseCheckoutDate("09/03/15")),
                3);
        addMock(new AgreementRequest("JAKR", 9, 0, parseCheckoutDate("07/02/15")),
                6);
        addMock(new AgreementRequest("JAKR", 4, 50, parseCheckoutDate("07/02/20")),
                1);
    }

    private void addMock(Tool tool) {
        mockToolMap.put(tool.getCode(), tool);
    }

    private void addMock(ToolCharges toolCharges) {
        mockToolChargesMap.put(toolCharges.getType(), toolCharges);
    }

    private void addMock(AgreementRequest request, int expectedChargeDays) {
        Tool tool = mockToolMap.get(request.getToolCode());
        ToolCharges toolCharges = mockToolChargesMap.get(tool.getType());

        LocalDate dueDate =  request.getCheckOutDate().plusDays(request.getDayCount());
        int preDiscountCharge = expectedChargeDays * toolCharges.getDailyCharge();
        float discountAmount = (float) request.getDiscountPercent() / 100 * preDiscountCharge;
        int roundedDiscountAmount = Math.round(discountAmount);
        int finalCharge = preDiscountCharge - roundedDiscountAmount;

        var agreement = new Agreement(
                tool.getCode(),
                tool.getType(),
                tool.getBrand(),
                request.getDayCount(),
                request.getCheckOutDate(),
                dueDate,
                toolCharges.getDailyCharge(),
                expectedChargeDays,
                preDiscountCharge,
                request.getDiscountPercent(),
                roundedDiscountAmount,
                finalCharge);

        MockRAList.add(agreement);
    }

    private LocalDate parseCheckoutDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yy"));
    }
}
