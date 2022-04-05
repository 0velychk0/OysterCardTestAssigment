package com.ovelychko.dto;

import java.util.List;

public enum StationType {
    Holborn(1),
    EarlCourt(1, 2),
    Wimbledon(3),
    Hammersmith(2),
    Chelsea(),
    undefined();

    public final List<Integer> zones;

    StationType(Integer... zones) {
        this.zones = List.of(zones);
    }
}
