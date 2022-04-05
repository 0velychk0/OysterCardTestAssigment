package com.ovelychko.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class FareTransaction {
    private final @NonNull TransportTypes transport;
    private final @NonNull StationType startStation;
    private final @NonNull StationType endStation;
}
