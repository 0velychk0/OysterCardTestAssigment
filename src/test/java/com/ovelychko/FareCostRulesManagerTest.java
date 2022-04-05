package com.ovelychko;

import com.ovelychko.Rules.FareTaxCalculatorRule;
import com.ovelychko.dto.FareTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FareCostRulesManagerTest {

    FareTaxCalculatorRule ruleTrue10;
    FareTaxCalculatorRule ruleTrue20;
    FareTaxCalculatorRule ruleFalse30;
    FareTaxCalculatorRule ruleFalse40;
    FareTransaction transaction;

    @BeforeEach
    void setUp() {
        transaction = Mockito.mock(FareTransaction.class);

        ruleTrue10 = Mockito.mock(FareTaxCalculatorRule.class);
        Mockito.when(ruleTrue10.getCost()).thenReturn(10.0);
        Mockito.when(ruleTrue10.test(Mockito.any())).thenReturn(true);

        ruleTrue20 = Mockito.mock(FareTaxCalculatorRule.class);
        Mockito.when(ruleTrue20.getCost()).thenReturn(20.0);
        Mockito.when(ruleTrue20.test(Mockito.any())).thenReturn(true);

        ruleFalse30 = Mockito.mock(FareTaxCalculatorRule.class);
        Mockito.when(ruleFalse30.getCost()).thenReturn(30.0);
        Mockito.when(ruleFalse30.test(Mockito.any())).thenReturn(false);

        ruleFalse40 = Mockito.mock(FareTaxCalculatorRule.class);
        Mockito.when(ruleFalse40.getCost()).thenReturn(40.0);
        Mockito.when(ruleFalse40.test(Mockito.any())).thenReturn(false);
    }

    @Test
    void testCalculateFareTransactionFalse() {
        FareCostRulesManager manager = new FareCostRulesManager();
        manager.listFareTaxCalculatorRule.add(ruleFalse30);
        Assertions.assertEquals(manager.getMaximumFareToCharge(), manager.calculateFareTransaction(transaction));

        manager.listFareTaxCalculatorRule.add(ruleFalse40);
        Assertions.assertEquals(manager.getMaximumFareToCharge(), manager.calculateFareTransaction(transaction));
    }

    @Test
    void testCalculateFareTransactionTrue() {
        FareCostRulesManager manager = new FareCostRulesManager();

        manager.listFareTaxCalculatorRule.add(ruleTrue20);
        Assertions.assertEquals(ruleTrue20.getCost(), manager.calculateFareTransaction(transaction));

        manager.listFareTaxCalculatorRule.add(ruleTrue10);
        Assertions.assertEquals(ruleTrue10.getCost(), manager.calculateFareTransaction(transaction));
    }

    @Test
    void testCalculateFareTransactionMixedRules() {
        FareCostRulesManager manager = new FareCostRulesManager();
        manager.listFareTaxCalculatorRule.add(ruleFalse30);
        Assertions.assertEquals(manager.getMaximumFareToCharge(), manager.calculateFareTransaction(transaction));

        manager.listFareTaxCalculatorRule.add(ruleTrue20);
        Assertions.assertEquals(ruleTrue20.getCost(), manager.calculateFareTransaction(transaction));

        manager.listFareTaxCalculatorRule.add(ruleTrue10);
        Assertions.assertEquals(ruleTrue10.getCost(), manager.calculateFareTransaction(transaction));
    }
}