package com.oocl.cultivation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperSmartParkingBoy extends ParkingBoy {
    Map<ParkingLot, Float> availablePositionRate = new HashMap<>();
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
            for (int i = 1; i < parkingLots.size(); i++) {
                if (calculateAvailablePositionRate(candidateLot) < calculateAvailablePositionRate(parkingLots.get(i))
                        && parkingLots.get(i).getAvailableParkingPosition() <= 0)
                    candidateLot = parkingLots.get(i);
            }
            carParkingLotAssociation.put(car, candidateLot);
            return candidateLot;
        }
        return parkingLot;
    }

    private float calculateAvailablePositionRate(ParkingLot parkingLot) {
        return (float) (parkingLot.getAvailableParkingPosition() / parkingLot.getCapacity());
    }

}
