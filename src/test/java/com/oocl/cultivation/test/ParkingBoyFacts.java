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
    void should_return_car_when_parkingBoy_fetch_car() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket ticket = parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(ticket);

        assertEquals(car, fetchedCar);
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

    @Test
    void should_return_null_when_null_ticket_provided() {
        Car otherCar1 = new Car();
        Car otherCar2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingBoy.park(otherCar1);
        parkingBoy.park(otherCar2);

        Car actual = parkingBoy.fetch(null);

        assertNull(actual);
    }

    @Test
    void should_return_null_when_ticket_has_already_been_used() {
        Car myCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket ticket = parkingBoy.park(myCar);
        parkingBoy.fetch(ticket);

        Car actual = parkingBoy.fetch(ticket);

        assertNull(actual);
    }
    @Test
    void should_return_null_when_no_parking_space_available() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        for (int i = 0; i < 10; i++) {
            parkingBoy.park(new Car());
        }

        Car myCar = new Car();
        ParkingTicket ticket = parkingBoy.park(myCar);

        assertNull(ticket);
    }
    @Test
    void should_return_null_when_parkingboy_park_an_already_parked_car() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car myCar = new Car();
        parkingBoy.park(myCar);
        ParkingTicket ticket = parkingBoy.park(myCar);

        assertNull(ticket);
    }
    @Test
    void should_return_null_when_parkingboy_park_nonexistent_car() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car myCar = new Car();
        parkingBoy.park(myCar);
        ParkingTicket ticket = parkingBoy.park(myCar);

        assertNull(ticket);
    }
}
