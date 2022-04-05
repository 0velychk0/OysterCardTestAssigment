package com.ovelychko.Rules;

import com.ovelychko.dto.FareTransaction;
import com.ovelychko.dto.StationType;
import com.ovelychko.dto.TransportTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsSameZoneNotEqualToTransactionRuleTest {

    @Test
    void test1() {
        FareTransaction fareTransaction = new FareTransaction(TransportTypes.Bus, StationType.EarlCourt, StationType.Hammersmith);

        IsSameZoneNotEqualToTransactionRule rule = new IsSameZoneNotEqualToTransactionRule(StationType.EarlCourt.zones.get(0));

        Assertions.assertTrue(rule.test(fareTransaction));
    }

    @Test
    void test2() {
        FareTransaction fareTransaction = new FareTransaction(TransportTypes.Bus, StationType.EarlCourt, StationType.Hammersmith);

        IsSameZoneNotEqualToTransactionRule rule = new IsSameZoneNotEqualToTransactionRule(StationType.Wimbledon.zones.get(0));

        Assertions.assertTrue(rule.test(fareTransaction));
    }

    @Test
    void test3() {
        FareTransaction fareTransaction = new FareTransaction(TransportTypes.Bus, StationType.EarlCourt, StationType.Hammersmith);

        IsSameZoneNotEqualToTransactionRule rule = new IsSameZoneNotEqualToTransactionRule(StationType.Hammersmith.zones.get(0));

        Assertions.assertFalse(rule.test(fareTransaction));
    }
}