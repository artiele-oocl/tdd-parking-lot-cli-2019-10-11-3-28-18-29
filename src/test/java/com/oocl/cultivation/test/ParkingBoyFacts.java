package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    void should_return_ticket_when_parkingBoy_park_car() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        ParkingTicket ticket = parkingBoy.park(car);

        assertNotNull(ticket);
    }

    @Test
    void should_return_ticket_when_parkingBoy_fetch_car() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket ticket = parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(ticket);

        assertNotNull(fetchedCar);
    }

    @Test
    void should_return_3_tickets_when_parkingBoy_park_3_cars() {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot = new ParkingLot();

        parkingLot.addCar(car1);
        parkingLot.addCar(car2);
        parkingLot.addCar(car3);
        int actual = parkingLot.getCarsCount();

        assertEquals(3, actual);
    }

    @Test
    void should_return_car_from_corresponding_ticket_when_parkingBoy_fetch_cars() {
        Car myCar = new Car();
        Car otherCar1 = new Car();
        Car otherCar2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingBoy.park(otherCar1);
        parkingBoy.park(otherCar2);
        ParkingTicket ticket = parkingBoy.park(myCar);

        Car actual = parkingBoy.fetch(ticket);

        assertEquals(myCar, actual);
    }
}
