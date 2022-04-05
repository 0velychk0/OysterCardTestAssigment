package com.ovelychko.Rules;

import com.ovelychko.dto.FareTransaction;
import com.ovelychko.dto.StationType;
import com.ovelychko.dto.TransportTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsSameZoneEqualToTransactionRuleTest {

    @Test
    void test1() {
        FareTransaction fareTransaction = new FareTransaction(TransportTypes.Bus, StationType.EarlCourt, StationType.Hammersmith);

        IsSameZoneEqualToTransactionRule rule = new IsSameZoneEqualToTransactionRule(StationType.Hammersmith.zones.get(0));

        Assertions.assertTrue(rule.test(fareTransaction));
    }

    @Test
    void test2() {
        FareTransaction fareTransaction = new FareTransaction(TransportTypes.Bus, StationType.EarlCourt, StationType.Hammersmith);

        IsSameZoneEqualToTransactionRule rule = new IsSameZoneEqualToTransactionRule(StationType.EarlCourt.zones.get(0));

        Assertions.assertFalse(rule.test(fareTransaction));
    }

    @Test
    void test3() {
        FareTransaction fareTransaction = new FareTransaction(TransportTypes.Bus, StationType.Wimbledon, StationType.Hammersmith);

        IsSameZoneEqualToTransactionRule rule = new IsSameZoneEqualToTransactionRule(StationType.Wimbledon.zones.get(0));

        Assertions.assertFalse(rule.test(fareTransaction));
    }
}