package com.ovelychko.Rules;

import com.ovelychko.dto.FareTransaction;
import com.ovelychko.dto.StationType;
import com.ovelychko.dto.TransportTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransportTransactionRuleTest {

    @Test
    void testBusRule() {
        TransportTransactionRule rule = new TransportTransactionRule(TransportTypes.Bus);

        FareTransaction busTransaction = new FareTransaction(TransportTypes.Bus, StationType.EarlCourt, StationType.Holborn);

        Assertions.assertTrue(rule.test(busTransaction));

        FareTransaction subTransaction = new FareTransaction(TransportTypes.Tube, StationType.EarlCourt, StationType.Holborn);

        Assertions.assertFalse(rule.test(subTransaction));
    }

    @Test
    void testSubwayRule() {
        TransportTransactionRule rule = new TransportTransactionRule(TransportTypes.Tube);

        FareTransaction subTransaction = new FareTransaction(TransportTypes.Tube, StationType.EarlCourt, StationType.Holborn);

        Assertions.assertTrue(rule.test(subTransaction));

        FareTransaction busTransaction = new FareTransaction(TransportTypes.Bus, StationType.EarlCourt, StationType.Holborn);

        Assertions.assertFalse(rule.test(busTransaction));
    }
}