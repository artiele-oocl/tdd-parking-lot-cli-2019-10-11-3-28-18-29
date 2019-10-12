package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(car1);
        parkingBoy.park(car2);
        parkingBoy.park(car3);
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

        assertSame(myCar, actual);
    }

    @Test
    void should_return_errMsg_when_null_ticket_provided() {
        Car otherCar1 = new Car();
        Car otherCar2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingBoy.park(otherCar1);
        parkingBoy.park(otherCar2);

        assertThrows(NullPointerException.class, ()->parkingBoy.fetch(null), parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_return_errMsg_when_ticket_has_already_been_used() {
        Car myCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket ticket = parkingBoy.park(myCar);
        parkingBoy.fetch(ticket);

        assertThrows(NullPointerException.class, ()->parkingBoy.fetch(ticket), parkingBoy.getLastErrorMessage());
    }
    @Test
    void should_return_errMsg_when_no_parking_space_available() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        for (int i = 0; i < 10; i++) {
            parkingBoy.park(new Car());
        }

        Car myCar = new Car();

        assertThrows(NullPointerException.class, ()->parkingBoy.park(myCar), parkingBoy.getLastErrorMessage());
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

        parkingBoy.park(null);
        ParkingTicket ticket = parkingBoy.park(null);

        assertNull(ticket);
    }
    @Test
    void should_park_on_first_parking_lot_when_first_parking_lot_isnot_yet_full() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car myCar = new Car();
        parkingBoy.park(myCar);

        assertSame(parkingLot1, parkingBoy.getCarParkingLotAssociation(myCar));
    }
    @Test
    void should_park_on_second_parking_lot_when_first_parking_lot_is_full() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        for (int i = 0; i < 15; i++) {
            parkingBoy.park(new Car());
        }

        Car myCar = new Car();
        parkingBoy.park(myCar);

        assertSame(parkingLot2, parkingBoy.getCarParkingLotAssociation(myCar));
    }
    @Test
    void smartParkingBoy_should_park_on_second_parking_lot_when_second_parking_lot_has_more_available_positions() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        for (int i = 0; i < 7; i++) {
            smartParkingBoy.park(new Car());
        }

        Car myCar = new Car();
        smartParkingBoy.park(myCar);

        assertSame(parkingLot2, smartParkingBoy.getCarParkingLotAssociation(myCar));
    }
    @Test
    void smartParkingBoy_should_park_on_first_parking_lot_when_both_parking_lots_have_equal_available_positions() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        for (int i = 0; i < 8; i++) {
            smartParkingBoy.park(new Car());
        }

        Car myCar = new Car();
        smartParkingBoy.park(myCar);

        assertSame(parkingLot1, smartParkingBoy.getCarParkingLotAssociation(myCar));
    }
    @Test
    void smartParkingBoy_should_return_ticket_when_parkingBoy_park_car() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);

        ParkingTicket ticket = smartParkingBoy.park(car);

        assertNotNull(ticket);
    }

    @Test
    void smartParkingBoy_should_return_car_when_parkingBoy_fetch_car() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        ParkingTicket ticket = smartParkingBoy.park(car);

        Car fetchedCar = smartParkingBoy.fetch(ticket);

        assertEquals(car, fetchedCar);
    }

    @Test
    void smartParkingBoy_should_return_3_tickets_when_parkingBoy_park_3_cars() {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(car1);
        parkingBoy.park(car2);
        parkingBoy.park(car3);
        int actual = parkingLot.getCarsCount();

        assertEquals(3, actual);
    }

    @Test
    void smartParkingBoy_should_return_car_from_corresponding_ticket_when_parkingBoy_fetch_cars() {
        Car myCar = new Car();
        Car otherCar1 = new Car();
        Car otherCar2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        smartParkingBoy.park(otherCar1);
        smartParkingBoy.park(otherCar2);
        ParkingTicket ticket = smartParkingBoy.park(myCar);

        Car actual = smartParkingBoy.fetch(ticket);

        assertSame(myCar, actual);
    }

    @Test
    void smartParkingBoy_should_return_errMsg_when_null_ticket_provided() {
        Car otherCar1 = new Car();
        Car otherCar2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        smartParkingBoy.park(otherCar1);
        smartParkingBoy.park(otherCar2);

        assertThrows(NullPointerException.class, ()->smartParkingBoy.fetch(null), smartParkingBoy.getLastErrorMessage());
    }

    @Test
    void smartParkingBoy_should_return_errMsg_when_ticket_has_already_been_used() {
        Car myCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        ParkingTicket ticket = smartParkingBoy.park(myCar);
        smartParkingBoy.fetch(ticket);

        assertThrows(NullPointerException.class, ()->smartParkingBoy.fetch(ticket), smartParkingBoy.getLastErrorMessage());
    }
    @Test
    void smartParkingBoy_should_return_errMsg_when_no_parking_space_available() {
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        for (int i = 0; i < 10; i++) {
            smartParkingBoy.park(new Car());
        }

        Car myCar = new Car();

        assertThrows(NullPointerException.class, ()->smartParkingBoy.park(myCar), smartParkingBoy.getLastErrorMessage());
    }
    @Test
    void smartParkingBoy_should_return_null_when_parkingboy_park_an_already_parked_car() {
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);

        Car myCar = new Car();
        smartParkingBoy.park(myCar);
        ParkingTicket ticket = smartParkingBoy.park(myCar);

        assertNull(ticket);
    }
    @Test
    void smartParkingBoy_should_return_null_when_parkingboy_park_nonexistent_car() {
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);

        smartParkingBoy.park(null);
        ParkingTicket ticket = smartParkingBoy.park(null);

        assertNull(ticket);
    }
    @Test
    void smartParkingBoy_should_park_on_first_parking_lot_when_first_parking_lot_isnot_yet_full() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car myCar = new Car();
        smartParkingBoy.park(myCar);

        assertSame(parkingLot1, smartParkingBoy.getCarParkingLotAssociation(myCar));
    }
    @Test
    void superSmartParkingBoy_should_park_on_first_parking_lot_when_it_has_larger_available_position_rate() {
        ParkingLot parkingLot1 = new ParkingLot(100);
        ParkingLot parkingLot2 = new ParkingLot(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        for (int i = 0; i < 4; i++) {
            superSmartParkingBoy.park(new Car());
        }

        Car myCar = new Car();
        superSmartParkingBoy.park(myCar);

        assertSame(parkingLot1, superSmartParkingBoy.getCarParkingLotAssociation(myCar));
    }
    @Test
    void superSmartParkingBoy_should_return_ticket_when_parkingBoy_park_car() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);

        ParkingTicket ticket = superSmartParkingBoy.park(car);

        assertNotNull(ticket);
    }

    @Test
    void superSmartParkingBoy_should_return_car_when_parkingBoy_fetch_car() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        ParkingTicket ticket = superSmartParkingBoy.park(car);

        Car fetchedCar = superSmartParkingBoy.fetch(ticket);

        assertEquals(car, fetchedCar);
    }

    @Test
    void superSmartParkingBoy_should_return_3_tickets_when_parkingBoy_park_3_cars() {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);

        superSmartParkingBoy.park(car1);
        superSmartParkingBoy.park(car2);
        superSmartParkingBoy.park(car3);
        int actual = parkingLot.getCarsCount();

        assertEquals(3, actual);
    }

    @Test
    void superSmartParkingBoy_should_return_car_from_corresponding_ticket_when_parkingBoy_fetch_cars() {
        Car myCar = new Car();
        Car otherCar1 = new Car();
        Car otherCar2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        superSmartParkingBoy.park(otherCar1);
        superSmartParkingBoy.park(otherCar2);
        ParkingTicket ticket = superSmartParkingBoy.park(myCar);

        Car actual = superSmartParkingBoy.fetch(ticket);

        assertSame(myCar, actual);
    }

    @Test
    void superSmartParkingBoy_should_return_errMsg_when_null_ticket_provided() {
        Car otherCar1 = new Car();
        Car otherCar2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        superSmartParkingBoy.park(otherCar1);
        superSmartParkingBoy.park(otherCar2);

        assertThrows(NullPointerException.class, ()->superSmartParkingBoy.fetch(null), superSmartParkingBoy.getLastErrorMessage());
    }

    @Test
    void superSmartParkingBoy_should_return_errMsg_when_ticket_has_already_been_used() {
        Car myCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        ParkingTicket ticket = superSmartParkingBoy.park(myCar);
        superSmartParkingBoy.fetch(ticket);

        assertThrows(NullPointerException.class, ()->superSmartParkingBoy.fetch(ticket), superSmartParkingBoy.getLastErrorMessage());
    }
    @Test
    void superSmartParkingBoy_should_return_errMsg_when_no_parking_space_available() {
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        for (int i = 0; i < 10; i++) {
            superSmartParkingBoy.park(new Car());
        }

        Car myCar = new Car();

        assertThrows(NullPointerException.class, ()->superSmartParkingBoy.park(myCar), superSmartParkingBoy.getLastErrorMessage());
    }
    @Test
    void superSmartParkingBoy_should_return_null_when_parkingboy_park_an_already_parked_car() {
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);

        Car myCar = new Car();
        superSmartParkingBoy.park(myCar);
        ParkingTicket ticket = superSmartParkingBoy.park(myCar);

        assertNull(ticket);
    }
    @Test
    void superSmartParkingBoy_should_return_null_when_parkingboy_park_nonexistent_car() {
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);

        superSmartParkingBoy.park(null);
        ParkingTicket ticket = superSmartParkingBoy.park(null);

        assertNull(ticket);
    }
    @Test
    void superSmartParkingBoy_should_park_on_first_parking_lot_when_first_parking_lot_isnot_yet_full() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        Car myCar = new Car();
        superSmartParkingBoy.park(myCar);

        assertSame(parkingLot1, superSmartParkingBoy.getCarParkingLotAssociation(myCar));
    }

}
