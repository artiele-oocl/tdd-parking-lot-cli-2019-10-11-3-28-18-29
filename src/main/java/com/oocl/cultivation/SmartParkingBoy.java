package com.oocl.cultivation;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    ParkingLot getAvailableParkingLot(Car car) {
        if (parkingLots != null) {
            ParkingLot candidateLot = parkingLots.get(0);
            for (ParkingLot parkingLot : parkingLots) {
                if (candidateLot.getAvailableParkingPosition() > parkingLot.getAvailableParkingPosition())
                    candidateLot = parkingLot;
            }
            carParkingLotAssociation.put(car, candidateLot);
            return candidateLot;
        }
        return parkingLot;
    }
}
