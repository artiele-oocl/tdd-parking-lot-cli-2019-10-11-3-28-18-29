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

    int getAvailableParkingPosition() {
        return cars.size() - capacity;
    }

    public int getCarsCount() {
        return cars.size();
    }
    int getCapacity() { return capacity; }

    ParkingTicket addCar(Car car) {
        if (cars.containsValue(car)) return null;
        if (getAvailableParkingPosition() > -1) throw new NullPointerException("Not enough position.");
        ParkingTicket parkingTicket = new ParkingTicket();
        cars.put(parkingTicket,car);
        return parkingTicket;
    }

    Car fetchCar(ParkingTicket ticket) {
        if (ticket == null) throw new NullPointerException("Please provide your parking ticket.");
        if (cars.containsKey(ticket)) return cars.remove(ticket);
        throw new NullPointerException("Unrecognized parking ticket.");
    }

}
