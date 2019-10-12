package com.oocl.cultivation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingBoy {

    List<ParkingLot> parkingLots;
    Map<Car,ParkingLot> carParkingLotAssociation = new HashMap<>();
    ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return getAvailableParkingLot(car).addCar(car);
    }

    public Car fetch(ParkingTicket ticket) {
        return parkingLot.fetchCar(ticket);
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public ParkingLot getCarParkingLotAssociation(Car car) {
        return carParkingLotAssociation.get(car);
    }

    ParkingLot getAvailableParkingLot(Car car) {
        if (parkingLots != null) {
            for (ParkingLot parkingLot : parkingLots) {
                if (parkingLot.getAvailableParkingPosition() < 0) {
                    carParkingLotAssociation.put(car, parkingLot);
                    return parkingLot;
                }
            }
        }
        return parkingLot;
    }

}
