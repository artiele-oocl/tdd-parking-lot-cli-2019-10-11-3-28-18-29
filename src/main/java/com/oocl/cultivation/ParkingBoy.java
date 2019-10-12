package com.oocl.cultivation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingBoy {

    private List<ParkingLot> parkingLots;
    private Map<Car,ParkingLot> carParkingLotAssociation = new HashMap<>();
    private ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        if (parkingLots != null) {
            for (ParkingLot parkingLot : parkingLots) {
                if (parkingLot.getAvailableParkingPosition() < 0) {
                    carParkingLotAssociation.put(car,parkingLot);
                    return parkingLot.addCar(car);
                }
            }
        }
        return parkingLot.addCar(car);
    }

    public Car fetch(ParkingTicket ticket) {
        if (ticket == null) throw new NullPointerException("Please provide your parking ticket.");
        return parkingLot.fetchCar(ticket);
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public ParkingLot getCarParkingLotAssociation(Car car) {
        return carParkingLotAssociation.get(car);
    }
}
