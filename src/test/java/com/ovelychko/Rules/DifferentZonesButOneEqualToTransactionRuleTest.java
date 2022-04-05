package com.ovelychko.Rules;

import com.ovelychko.dto.FareTransaction;
import com.ovelychko.dto.StationType;
import com.ovelychko.dto.TransportTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DifferentZonesButOneEqualToTransactionRuleTest {

    @Test
    void test1() {
        FareTransaction busTransaction = new FareTransaction(TransportTypes.Bus, StationType.Wimbledon, StationType.Hammersmith);

        DifferentZonesButOneEqualToTransactionRule rule = new DifferentZonesButOneEqualToTransactionRule(StationType.Wimbledon.zones.get(0));

        Assertions.assertTrue(rule.test(busTransaction));
    }

    @Test
    void test2() {
        FareTransaction busTransaction = new FareTransaction(TransportTypes.Bus, StationType.Wimbledon, StationType.Hammersmith);

        DifferentZonesButOneEqualToTransactionRule rule = new DifferentZonesButOneEqualToTransactionRule(StationType.Hammersmith.zones.get(0));

        Assertions.assertTrue(rule.test(busTransaction));
    }

    @Test
    void test3() {
        FareTransaction busTransaction = new FareTransaction(TransportTypes.Bus, StationType.EarlCourt, StationType.Hammersmith);

        DifferentZonesButOneEqualToTransactionRule rule = new DifferentZonesButOneEqualToTransactionRule(StationType.Hammersmith.zones.get(0));

        Assertions.assertTrue(rule.test(busTransaction));
    }

    @Test
    void test4() {
        FareTransaction busTransaction = new FareTransaction(TransportTypes.Bus, StationType.EarlCourt, StationType.EarlCourt);

        DifferentZonesButOneEqualToTransactionRule rule = new DifferentZonesButOneEqualToTransactionRule(StationType.EarlCourt.zones.get(0));

        Assertions.assertTrue(rule.test(busTransaction));
    }

    @Test
    void test5() {
        FareTransaction busTransaction = new FareTransaction(TransportTypes.Bus, StationType.EarlCourt, StationType.Hammersmith);

        DifferentZonesButOneEqualToTransactionRule rule = new DifferentZonesButOneEqualToTransactionRule(StationType.Wimbledon.zones.get(0));

        Assertions.assertFalse(rule.test(busTransaction));
    }
}