package com.billing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testBasicPlanWithinLimit() {
        assertEquals(20.0, App.calculateBill("basic", 8.0), 0.001);
    }

    @Test
    public void testBasicPlanWithOverage() {
        // 20 base + (12 - 10) * 5 overage = 30.0
        assertEquals(30.0, App.calculateBill("basic", 12.0), 0.001);
    }

    @Test
    public void testStandardPlanWithOverage() {
        // 40 base + (60 - 50) * 3 overage = 70.0
        assertEquals(70.0, App.calculateBill("standard", 60.0), 0.001);
    }

    @Test
    public void testInvalidPlan() {
        assertThrows(IllegalArgumentException.class, () -> {
            App.calculateBill("invalid_plan", 10.0);
        });
    }
}
