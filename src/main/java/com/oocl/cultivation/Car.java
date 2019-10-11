package com.oocl.cultivation;

public class Car {
    private ParkingTicket parkingTicket;
    public ParkingTicket park() {
        return parkingTicket.giveTicket();
    }
}
