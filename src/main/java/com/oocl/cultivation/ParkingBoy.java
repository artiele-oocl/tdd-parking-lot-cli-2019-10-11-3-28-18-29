package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        return parkingLot.getAvailableParkingPosition() < 0 ? parkingLot.addCar(car) : null;
    }

    public Car fetch(ParkingTicket ticket) {
        Car fetchedCar = parkingLot.fetchCar(ticket);
        return fetchedCar;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
