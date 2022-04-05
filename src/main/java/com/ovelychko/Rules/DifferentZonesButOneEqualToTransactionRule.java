package com.ovelychko.Rules;

import com.ovelychko.dto.FareTransaction;

import java.util.function.Predicate;

// Rule: Any two zones including 'zone'
// Two zones should be different but one of them is 'zone'
public class DifferentZonesButOneEqualToTransactionRule implements Predicate<FareTransaction> {

    private final int zone;

    public DifferentZonesButOneEqualToTransactionRule(int zone) {
        this.zone = zone;
    }

    @Override
    public boolean test(FareTransaction fareTransaction) {
        return (fareTransaction.getStartStation().zones.stream().anyMatch(zn -> zn != zone) &&
                fareTransaction.getEndStation().zones.contains(zone))
                ||
                (fareTransaction.getEndStation().zones.stream().anyMatch(zn -> zn != zone) &&
                        fareTransaction.getStartStation().zones.contains(zone));
    }
}
