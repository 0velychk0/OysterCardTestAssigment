package com.ovelychko.Rules;

import com.ovelychko.dto.FareTransaction;

import java.util.List;
import java.util.function.Predicate;

// Rule: Any two zones excluding zone 1
// Two zones should be different no one of them is 'zone'
public class DifferentZonesNotEqualToTransactionRule implements Predicate<FareTransaction> {

    private final int zone;

    public DifferentZonesNotEqualToTransactionRule(int zone) {
        this.zone = zone;
    }

    @Override
    public boolean test(FareTransaction fareTransaction) {
        List<Integer> startZones = fareTransaction.getStartStation().zones.stream().filter(zn -> zn != zone).toList();
        List<Integer> endZones = fareTransaction.getEndStation().zones.stream().filter(zn -> zn != zone).toList();
        if (startZones.isEmpty() || endZones.isEmpty())
            return false;
        return startZones.stream().anyMatch(item -> !endZones.contains(item));
    }
}
