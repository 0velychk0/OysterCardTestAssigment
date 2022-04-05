package com.ovelychko.Rules;

import com.ovelychko.dto.FareTransaction;
import com.ovelychko.dto.TransportTypes;

import java.util.Objects;
import java.util.function.Predicate;

public class TransportTransactionRule implements Predicate<FareTransaction> {

    private final TransportTypes transportType;

    public TransportTransactionRule(TransportTypes transportType) {
        Objects.requireNonNull(transportType, "transportType cannot be null");
        this.transportType = transportType;
    }

    @Override
    public boolean test(FareTransaction fareTransaction) {
        return fareTransaction.getTransport().equals(transportType);
    }
}
