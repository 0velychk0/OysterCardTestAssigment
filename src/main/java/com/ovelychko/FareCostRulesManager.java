package com.ovelychko;

import com.ovelychko.Rules.FareTaxCalculatorRule;
import com.ovelychko.dto.FareTransaction;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class FareCostRulesManager {

    final List<FareTaxCalculatorRule> listFareTaxCalculatorRule = new ArrayList<>();
    @Getter
    private final double maximumFareToCharge = 3.20;

    public FareCostRulesManager() {
    }

    public double calculateFareTransaction(FareTransaction fareTransaction) {
        OptionalDouble minimumCost = listFareTaxCalculatorRule.stream()
                .filter(item -> item.test(fareTransaction))
                .mapToDouble(item -> item.getCost()).min();

        return minimumCost.orElse(maximumFareToCharge);
    }
}
