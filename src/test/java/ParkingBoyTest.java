import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class ParkingBoyTest {
    @Test
    public void should_throw_exception_when_pickup_with_invalid_ticket() {
        Ticket invalid = new Ticket();

        ParkingBoy parkingBoy = new ParkingBoy();
        Optional<Car> actual = parkingBoy.pickup(invalid);
        assertFalse(actual.isPresent());
    }

    @Test
    public void should_get_different_ticket_when_park_two_cars() {
        Car carA = new Car();
        Car carB = new Car();
        ParkingBoy parkingBoy = new ParkingBoy();
        assertThat(parkingBoy.park(carA).get(), not(equalTo(parkingBoy.park(carB).get())));
    }

    @Test
    public void should_get_the_car_by_ticket() {
        Car expected = new Car();

        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticket = parkingBoy.park(expected).get();
        Car actual = parkingBoy.pickup(ticket).get();

        assertThat(expected, is(actual));
    }

    @Test
    public void should_throw_exception_when_park_one_car_two_times() {
        Car car = new Car();

        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.park(car);
        Optional<Ticket> actual = parkingBoy.park(car);
        assertFalse(actual.isPresent());
    }

    @Test
    public void should_park_and_pickup_three_cars() {
        Car carA = new Car();
        Car carB = new Car();
        Car carC = new Car();

        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticketA = parkingBoy.park(carA).get();
        Ticket ticketB = parkingBoy.park(carB).get();
        Ticket ticketC = parkingBoy.park(carC).get();

        assertThat(carA, is(parkingBoy.pickup(ticketA).get()));
        assertThat(carB, is(parkingBoy.pickup(ticketB).get()));
        assertThat(carC, is(parkingBoy.pickup(ticketC).get()));
    }

    @Test
    public void should_return_optional_empty_when_park_one_time_and_pick_two_times() {
        Car car = new Car();

        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticket = parkingBoy.park(car).get();

        parkingBoy.pickup(ticket);
        Optional<Car> actual = parkingBoy.pickup(ticket);
        assertFalse(actual.isPresent());
    }

    @Test
    public void should_return_optional_empty_when_parking_lots_are_full() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(2), new ParkingLot(3));
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        Optional<Ticket> emptyTicket = parkingBoy.park(new Car());
        assertFalse(emptyTicket.isPresent());
    }

    @Test
    public void should_park_car_successfully_more_than_one_parking_lot_capacity() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(2), new ParkingLot(2));
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        Optional<Ticket> existedTicket = parkingBoy.park(new Car());
        assertTrue(existedTicket.isPresent());
    }

    @Test
    public void should_parking_boy_pick_up_car_parking_at_the_third_parking_lot() {
        ParkingLot thirdParkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(), new ParkingLot(), thirdParkingLot);
        Car expected = new Car();
        Ticket ticket = thirdParkingLot.park(expected).get();
        Car actual = parkingBoy.pickup(ticket).get();
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void should_park_car_according_to_the_order() {
        int initCapacity = 3;
        ParkingLot parkingLotA = new ParkingLot(initCapacity);
        ParkingLot parkingLotB = new ParkingLot(initCapacity);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA, parkingLotB);
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        assertThat(parkingLotB.getCapacity(), is(initCapacity));
    }
}
