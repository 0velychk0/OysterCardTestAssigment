package com.ovelychko;

import com.ovelychko.dto.FareTransaction;
import com.ovelychko.dto.StationType;
import com.ovelychko.dto.TransportTypes;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

import java.util.Objects;

@Log4j
public class UserCard {

    @Getter
    private double moneyValue;

    private StationType startStationLastUsed = StationType.undefined;
    private TransportTypes transportLastUsed = TransportTypes.undefined;
    private final FareCostRulesManager fareCostRulesManager = new LondonFareCostRulesManager();

    UserCard(double startMoneyValue) {
        this.moneyValue = startMoneyValue;
    }

    public void enterToStation(TransportTypes transport, StationType startStation) {
        Objects.requireNonNull(transport, "transport can't be null");
        Objects.requireNonNull(startStation, "startStation can't be null");

        moneyValue -= fareCostRulesManager.getMaximumFareToCharge();

        // Finish previous transaction
        if (startStationLastUsed != StationType.undefined && transportLastUsed != TransportTypes.undefined) {
            log.warn("Previous transaction is not closed, finish it with undefined station");
            exitFromStation(StationType.undefined);
        }

        this.transportLastUsed = transport;
        this.startStationLastUsed = startStation;
    }

    public void exitFromStation(StationType endStation) {
        Objects.requireNonNull(endStation, "endStation can't be null");

        if (startStationLastUsed.equals(StationType.undefined) ||
                transportLastUsed.equals(TransportTypes.undefined)) {
            log.warn("Try to finish transaction without correct start");
            // TODO: throw an exception!
        }

        FareTransaction transaction = new FareTransaction(this.transportLastUsed, this.startStationLastUsed, endStation);
        double calculatedCost = fareCostRulesManager.calculateFareTransaction(transaction);
        moneyValue = moneyValue + (fareCostRulesManager.getMaximumFareToCharge() - calculatedCost);

        this.startStationLastUsed = StationType.undefined;
        this.transportLastUsed = TransportTypes.undefined;
    }
}
