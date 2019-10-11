package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableParkingPosition() {
        return cars.size() - capacity;
    }

    public int getCarsCount() {
        return cars.size();
    }

    public ParkingTicket addCar(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        cars.put(parkingTicket,car);
        return parkingTicket;
    }

    public Car fetchCar(ParkingTicket ticket) {
        return cars.get(ticket);
    }
}
