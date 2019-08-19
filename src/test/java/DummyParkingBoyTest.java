import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class DummyParkingBoyTest {
    @Test
    public void should_throw_exception_when_pickup_with_invalid_ticket() {
        Ticket invalid = new Ticket();

        DummyParkingBoy dummyParkingBoy = new DummyParkingBoy();
        Optional<Car> actual = dummyParkingBoy.pickup(invalid);
        assertFalse(actual.isPresent());
    }

    @Test
    public void should_get_different_ticket_when_park_two_cars() {
        Car carA = new Car();
        Car carB = new Car();
        DummyParkingBoy dummyParkingBoy = new DummyParkingBoy();
        assertThat(dummyParkingBoy.park(carA).get(), not(equalTo(dummyParkingBoy.park(carB).get())));
    }

    @Test
    public void should_get_the_car_by_ticket() {
        Car expected = new Car();

        DummyParkingBoy dummyParkingBoy = new DummyParkingBoy();
        Ticket ticket = dummyParkingBoy.park(expected).get();
        Car actual = dummyParkingBoy.pickup(ticket).get();

        assertThat(expected, is(actual));
    }

    @Test
    public void should_throw_exception_when_park_one_car_two_times() {
        Car car = new Car();

        DummyParkingBoy dummyParkingBoy = new DummyParkingBoy();
        dummyParkingBoy.park(car);
        Optional<Ticket> actual = dummyParkingBoy.park(car);
        assertFalse(actual.isPresent());
    }

    @Test
    public void should_park_and_pickup_three_cars() {
        Car carA = new Car();
        Car carB = new Car();
        Car carC = new Car();

        DummyParkingBoy dummyParkingBoy = new DummyParkingBoy();
        Ticket ticketA = dummyParkingBoy.park(carA).get();
        Ticket ticketB = dummyParkingBoy.park(carB).get();
        Ticket ticketC = dummyParkingBoy.park(carC).get();

        assertThat(carA, is(dummyParkingBoy.pickup(ticketA).get()));
        assertThat(carB, is(dummyParkingBoy.pickup(ticketB).get()));
        assertThat(carC, is(dummyParkingBoy.pickup(ticketC).get()));
    }

    @Test
    public void should_return_optional_empty_when_park_one_time_and_pick_two_times() {
        Car car = new Car();

        DummyParkingBoy dummyParkingBoy = new DummyParkingBoy();
        Ticket ticket = dummyParkingBoy.park(car).get();

        dummyParkingBoy.pickup(ticket);
        Optional<Car> actual = dummyParkingBoy.pickup(ticket);
        assertFalse(actual.isPresent());
    }

    @Test
    public void should_return_optional_empty_when_parking_lots_are_full() {
        DummyParkingBoy dummyParkingBoy = new DummyParkingBoy(new ParkingLot(2), new ParkingLot(3));
        dummyParkingBoy.park(new Car());
        dummyParkingBoy.park(new Car());
        dummyParkingBoy.park(new Car());
        dummyParkingBoy.park(new Car());
        dummyParkingBoy.park(new Car());
        Optional<Ticket> emptyTicket = dummyParkingBoy.park(new Car());
        assertFalse(emptyTicket.isPresent());
    }

    @Test
    public void should_park_car_successfully_more_than_one_parking_lot_capacity() {
        DummyParkingBoy dummyParkingBoy = new DummyParkingBoy(new ParkingLot(2), new ParkingLot(2));
        dummyParkingBoy.park(new Car());
        dummyParkingBoy.park(new Car());
        Optional<Ticket> existedTicket = dummyParkingBoy.park(new Car());
        assertTrue(existedTicket.isPresent());
    }

    @Test
    public void should_parking_boy_pick_up_car_parking_at_the_third_parking_lot() {
        ParkingLot thirdParkingLot = new ParkingLot();
        DummyParkingBoy dummyParkingBoy = new DummyParkingBoy(new ParkingLot(), new ParkingLot(), thirdParkingLot);
        Car expected = new Car();
        Ticket ticket = thirdParkingLot.park(expected).get();
        Car actual = dummyParkingBoy.pickup(ticket).get();
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void should_park_car_according_to_the_order() {
        int initCapacity = 3;
        ParkingLot parkingLotA = new ParkingLot(initCapacity);
        ParkingLot parkingLotB = new ParkingLot(initCapacity);
        DummyParkingBoy dummyParkingBoy = new DummyParkingBoy(parkingLotA, parkingLotB);
        dummyParkingBoy.park(new Car());
        dummyParkingBoy.park(new Car());
        dummyParkingBoy.park(new Car());
        assertThat(parkingLotB.getCapacity(), is(initCapacity));
    }
}
