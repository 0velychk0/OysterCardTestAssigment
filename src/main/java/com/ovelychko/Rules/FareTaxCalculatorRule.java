package com.ovelychko.Rules;

import com.ovelychko.dto.FareTransaction;
import lombok.Getter;

import java.util.function.Predicate;

public class FareTaxCalculatorRule implements Predicate<FareTransaction> {

    @Getter
    private final double cost;

    private final Predicate<FareTransaction> transactionRule;

    public FareTaxCalculatorRule(double cost, Predicate<FareTransaction> transactionRule) {
        this.cost = cost;
        this.transactionRule = transactionRule;
    }

    public boolean test(FareTransaction transaction) {
        return transactionRule.test(transaction);
    }
}
