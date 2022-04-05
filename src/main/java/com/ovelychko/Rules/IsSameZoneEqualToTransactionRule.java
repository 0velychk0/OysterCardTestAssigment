package com.ovelychko.Rules;

import com.ovelychko.dto.FareTransaction;

import java.util.function.Predicate;

// Rule: Anywhere in "zone"
// Both zones are equal to "zone"
public class IsSameZoneEqualToTransactionRule implements Predicate<FareTransaction> {

    private final int zone;

    public IsSameZoneEqualToTransactionRule(int zone) {
        this.zone = zone;
    }

    @Override
    public boolean test(FareTransaction fareTransaction) {
        return (fareTransaction.getEndStation().zones.contains(zone) &&
                fareTransaction.getStartStation().zones.contains(zone));
    }
}