package com.ovelychko;

import com.ovelychko.dto.StationType;
import com.ovelychko.dto.TransportTypes;
import lombok.extern.log4j.Log4j;

@Log4j
public class MainTestClass {
    public static void main(String[] argv) {
        // user loading a card with £30
        UserCard userCard = new UserCard(30);
        log.info("Initial user card balance is £" + userCard.getMoneyValue());

        // Tube Holborn to Earl’s Court
        userCard.enterToStation(TransportTypes.Tube, StationType.Holborn);
        userCard.exitFromStation(StationType.EarlCourt);
        log.info("trip 'Tube Holborn to Earl’s Court', user card balance is: £" + userCard.getMoneyValue());

        // 328 bus from Earl’s Court to Chelsea
        userCard.enterToStation(TransportTypes.Bus, StationType.EarlCourt);
        userCard.exitFromStation(StationType.Chelsea);
        log.info("trip '328 bus from Earl’s Court to Chelsea', user card balance is: £" + userCard.getMoneyValue());

        // Tube Earl’s court to Hammersmith
        userCard.enterToStation(TransportTypes.Tube, StationType.EarlCourt);
        userCard.exitFromStation(StationType.Hammersmith);
        log.info("trip 'Tube Earl’s court to Hammersmith', user card balance is: £" + userCard.getMoneyValue());
    }
}
