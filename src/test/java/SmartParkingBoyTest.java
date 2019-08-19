import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SmartParkingBoyTest {
    @Test
    public void should_throw_exception_when_pickup_with_invalid_ticket() {
        Ticket invalid = new Ticket();

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Optional<Car> actual = smartParkingBoy.pickup(invalid);
        assertFalse(actual.isPresent());
    }

    @Test
    public void should_get_different_ticket_when_park_two_cars() {
        Car carA = new Car();
        Car carB = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        assertThat(smartParkingBoy.park(carA).get(), not(equalTo(smartParkingBoy.park(carB).get())));
    }

    @Test
    public void should_get_the_car_by_ticket() {
        Car expected = new Car();

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Ticket ticket = smartParkingBoy.park(expected).get();
        Car actual = smartParkingBoy.pickup(ticket).get();

        assertThat(expected, is(actual));
    }

    @Test
    public void should_throw_exception_when_park_one_car_two_times() {
        Car car = new Car();

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.park(car);
        Optional<Ticket> actual = smartParkingBoy.park(car);
        assertFalse(actual.isPresent());
    }

    @Test
    public void should_park_and_pickup_three_cars() {
        Car carA = new Car();
        Car carB = new Car();
        Car carC = new Car();

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Ticket ticketA = smartParkingBoy.park(carA).get();
        Ticket ticketB = smartParkingBoy.park(carB).get();
        Ticket ticketC = smartParkingBoy.park(carC).get();

        assertThat(carA, is(smartParkingBoy.pickup(ticketA).get()));
        assertThat(carB, is(smartParkingBoy.pickup(ticketB).get()));
        assertThat(carC, is(smartParkingBoy.pickup(ticketC).get()));
    }

    @Test
    public void should_return_optional_empty_when_park_one_time_and_pick_two_times() {
        Car car = new Car();

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Ticket ticket = smartParkingBoy.park(car).get();

        smartParkingBoy.pickup(ticket);
        Optional<Car> actual = smartParkingBoy.pickup(ticket);
        assertFalse(actual.isPresent());
    }

    @Test
    public void should_return_optional_empty_when_parking_lots_are_full() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(2), new ParkingLot(3));
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        Optional<Ticket> emptyTicket = smartParkingBoy.park(new Car());
        assertFalse(emptyTicket.isPresent());
    }

    @Test
    public void should_park_car_successfully_more_than_one_parking_lot_capacity() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(2), new ParkingLot(2));
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        Optional<Ticket> existedTicket = smartParkingBoy.park(new Car());
        assertTrue(existedTicket.isPresent());
    }

    @Test
    public void should_parking_boy_pick_up_car_parking_at_the_third_parking_lot() {
        ParkingLot thirdParkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(), new ParkingLot(), thirdParkingLot);
        Car expected = new Car();
        Ticket ticket = thirdParkingLot.park(expected).get();
        Car actual = smartParkingBoy.pickup(ticket).get();
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void should_park_car_to_parking_lotB_when_parking_lotB_remains_more_capacity() {
        int initCapacity = 3;
        ParkingLot parkingLotA = new ParkingLot(initCapacity);
        ParkingLot parkingLotB = new ParkingLot(initCapacity);
        parkingLotA.park(new Car());
        parkingLotA.park(new Car());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotA, parkingLotB);
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        assertThat(parkingLotA.getCapacity(), is(1));
        assertThat(parkingLotB.getCapacity(), is(1));
    }

    @Test
    public void should_park_car_to_parking_lotA_when_parking_lotA_remains_more_capacity() {
        int initCapacity = 3;
        ParkingLot parkingLotA = new ParkingLot(initCapacity);
        ParkingLot parkingLotB = new ParkingLot(initCapacity);
        parkingLotB.park(new Car());
        parkingLotB.park(new Car());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotA, parkingLotB);
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        assertThat(parkingLotB.getCapacity(), is(1));
        assertThat(parkingLotA.getCapacity(), is(1));
    }

    @Test
    public void should_park_car_to_parking_lotA_when_parking_lotA_and_parking_lotB_has_the_same_capacity() {
        int initCapacity = 3;
        ParkingLot parkingLotA = new ParkingLot(initCapacity);
        ParkingLot parkingLotB = new ParkingLot(initCapacity);
        parkingLotA.park(new Car());
        parkingLotA.park(new Car());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotA, parkingLotB);
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        assertThat(parkingLotA.getCapacity(), is(0));
        assertThat(parkingLotB.getCapacity(), is(1));
    }
}
