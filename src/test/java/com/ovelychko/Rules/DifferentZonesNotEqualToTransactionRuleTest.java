package com.ovelychko.Rules;

import com.ovelychko.dto.FareTransaction;
import com.ovelychko.dto.StationType;
import com.ovelychko.dto.TransportTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DifferentZonesNotEqualToTransactionRuleTest {

    @Test
    void test1() {
        FareTransaction busTransaction = new FareTransaction(TransportTypes.Bus, StationType.Wimbledon, StationType.Hammersmith);

        DifferentZonesNotEqualToTransactionRule rule = new DifferentZonesNotEqualToTransactionRule(StationType.Holborn.zones.get(0));

        Assertions.assertTrue(rule.test(busTransaction));
    }

    @Test
    void test2() {
        FareTransaction busTransaction = new FareTransaction(TransportTypes.Bus, StationType.EarlCourt, StationType.Hammersmith);

        DifferentZonesNotEqualToTransactionRule rule = new DifferentZonesNotEqualToTransactionRule(StationType.EarlCourt.zones.get(0));

        Assertions.assertFalse(rule.test(busTransaction));
    }

    @Test
    void test3() {
        FareTransaction busTransaction = new FareTransaction(TransportTypes.Bus, StationType.EarlCourt, StationType.Hammersmith);

        DifferentZonesNotEqualToTransactionRule rule = new DifferentZonesNotEqualToTransactionRule(StationType.Hammersmith.zones.get(0));

        Assertions.assertFalse(rule.test(busTransaction));
    }

    @Test
    void test4() {
        FareTransaction busTransaction = new FareTransaction(TransportTypes.Bus, StationType.Holborn, StationType.Hammersmith);

        DifferentZonesNotEqualToTransactionRule rule = new DifferentZonesNotEqualToTransactionRule(StationType.Wimbledon.zones.get(0));

        Assertions.assertTrue(rule.test(busTransaction));
    }
}
