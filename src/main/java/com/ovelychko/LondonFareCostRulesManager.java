package com.ovelychko;

import com.ovelychko.Rules.*;
import com.ovelychko.dto.TransportTypes;

public class LondonFareCostRulesManager extends FareCostRulesManager {

    public LondonFareCostRulesManager() {
        super();
        // Anywhere in Zone 1 by subway is £2.50
        listFareTaxCalculatorRule.add(
                new FareTaxCalculatorRule(2.50,
                        new TransportTransactionRule(TransportTypes.Tube)
                                .and(new IsSameZoneEqualToTransactionRule(1)))
        );

        // Any one zone outside zone 1 by subway is £2.00
        listFareTaxCalculatorRule.add(
                new FareTaxCalculatorRule(2.00,
                        new TransportTransactionRule(TransportTypes.Tube)
                                .and(new IsSameZoneNotEqualToTransactionRule(1)))
        );
        // Any two zones including zone 1 is £3.00
        listFareTaxCalculatorRule.add(
                new FareTaxCalculatorRule(3.00,
                        new TransportTransactionRule(TransportTypes.Tube)
                                .and(new DifferentZonesButOneEqualToTransactionRule(1)))
        );
        // Any two zones excluding zone 1 is £2.25
        listFareTaxCalculatorRule.add(
                new FareTaxCalculatorRule(2.25,
                        new TransportTransactionRule(TransportTypes.Tube)
                                .and(new DifferentZonesNotEqualToTransactionRule(1)))
        );
        // Any three zones is £3.20
        listFareTaxCalculatorRule.add(
                new FareTaxCalculatorRule(3.20,
                        new TransportTransactionRule(TransportTypes.Tube))
        );
        // Any bus journey is £1.80
        listFareTaxCalculatorRule.add(
                new FareTaxCalculatorRule(1.80,
                        new TransportTransactionRule(TransportTypes.Bus))
        );
    }
}
