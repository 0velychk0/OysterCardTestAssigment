package com.ovelychko.Rules;

import com.ovelychko.dto.FareTransaction;

import java.util.function.Predicate;

// Rule: Any one zone outside 'zone'
// Both stations in same zone which is not equal to 'zone'
public class IsSameZoneNotEqualToTransactionRule implements Predicate<FareTransaction> {

    private final int zone;

    public IsSameZoneNotEqualToTransactionRule(int zone) {
        this.zone = zone;
    }

    @Override
    public boolean test(FareTransaction fareTransaction) {
        return fareTransaction.getEndStation().zones.stream()
                .distinct()
                .filter(fareTransaction.getStartStation().zones::contains)
                .anyMatch(item -> (item != zone));
    }
}