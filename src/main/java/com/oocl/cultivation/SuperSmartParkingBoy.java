package com.oocl.cultivation;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    ParkingLot getChosenParkingLot(Car car) {
        if (parkingLots != null) {
            ParkingLot candidateLot = parkingLots.get(0);
            for (ParkingLot parkingLot : parkingLots) {
                if (candidateLot.calculateAvailablePositionRate() < parkingLot.calculateAvailablePositionRate())
                    candidateLot = parkingLot;
            }
            carParkingLotAssociation.put(car, candidateLot);
            return candidateLot;
        }
        return parkingLot;
    }
}
