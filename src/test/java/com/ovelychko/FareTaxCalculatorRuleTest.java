package com.ovelychko;

import com.ovelychko.Rules.FareTaxCalculatorRule;
import com.ovelychko.dto.FareTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Predicate;

class FareTaxCalculatorRuleTest {

    Predicate<FareTransaction> trueTransaction1 = Mockito.spy(Predicate.class);
    Predicate<FareTransaction> trueTransaction2 = Mockito.spy(Predicate.class);
    Predicate<FareTransaction> falseTransaction1 = Mockito.spy(Predicate.class);
    Predicate<FareTransaction> falseTransaction2 = Mockito.spy(Predicate.class);
    FareTransaction fareTransaction = Mockito.mock(FareTransaction.class);

    double mockCost = 10.0;

    @BeforeEach
    void setUp() {
        Mockito.when(trueTransaction1.test(Mockito.any())).thenReturn(true);
        Mockito.when(trueTransaction2.test(Mockito.any())).thenReturn(true);
        Mockito.when(falseTransaction1.test(Mockito.any())).thenReturn(false);
        Mockito.when(falseTransaction2.test(Mockito.any())).thenReturn(false);
    }

    @Test
    void tesCheckCostGetter() {
        FareTaxCalculatorRule rule = new FareTaxCalculatorRule(mockCost, trueTransaction1);

        Assertions.assertEquals(mockCost, rule.getCost());
    }

    @Test
    void testCalculateAllPassed() {
        FareTaxCalculatorRule rule = new FareTaxCalculatorRule(mockCost, trueTransaction1.and(trueTransaction2));

        Assertions.assertTrue(rule.test(fareTransaction));
    }

    @Test
    void testCalculateAllFailed() {
        FareTaxCalculatorRule rule = new FareTaxCalculatorRule(mockCost, falseTransaction1.and(falseTransaction1));

        Assertions.assertFalse(rule.test(fareTransaction));
    }

    @Test
    void testCalculateMixedFailed() {
        FareTaxCalculatorRule rule = new FareTaxCalculatorRule(mockCost, trueTransaction1.and(falseTransaction1));

        Assertions.assertFalse(rule.test(fareTransaction));
    }

    @Test
    void testCalculateMixedFailed2() {
        FareTaxCalculatorRule rule = new FareTaxCalculatorRule(mockCost, falseTransaction1.and(trueTransaction1));

        Assertions.assertFalse(rule.test(fareTransaction));
    }
}
