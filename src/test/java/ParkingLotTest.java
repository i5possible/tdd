import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class ParkingLotTest {

    @Test
    public void should_return_optional_empty_when_pickup_with_invalid_ticket() {
        Ticket invalid = new Ticket();

        ParkingLot parkingService = new ParkingLot();
        Optional<Car> car = parkingService.pickup(invalid);
        assertFalse(car.isPresent());
    }

    @Test
    public void should_get_different_ticket_when_park_two_cars() {
        Car carA = new Car();
        Car carB = new Car();
        ParkingLot parkingService = new ParkingLot();
        assertThat(parkingService.park(carA), not(equalTo(parkingService.park(carB))));
    }

    @Test
    public void should_get_the_car_by_ticket() {
        Car expected = new Car();

        ParkingLot parkingService = new ParkingLot();
        Optional<Ticket> ticket = parkingService.park(expected);
        Car actual = parkingService.pickup(ticket.get()).get();

        assertThat(expected, is(actual));
    }

    @Test
    public void should_return_empty_when_park_one_car_two_times() {
        Car car = new Car();

        ParkingLot parkingService = new ParkingLot();
        parkingService.park(car);
        Optional<Ticket> actual = parkingService.park(car);
        assertFalse(actual.isPresent());
    }

    @Test
    public void should_park_and_pickup_three_cars() {
        Car carA = new Car();
        Car carB = new Car();
        Car carC = new Car();

        ParkingLot parkingService = new ParkingLot();
        Ticket ticketA = parkingService.park(carA).get();
        Ticket ticketB = parkingService.park(carB).get();
        Ticket ticketC = parkingService.park(carC).get();

        assertThat(carA, is(parkingService.pickup(ticketA).get()));
        assertThat(carB, is(parkingService.pickup(ticketB).get()));
        assertThat(carC, is(parkingService.pickup(ticketC).get()));
    }

    @Test
    public void should_return_optional_empty_when_park_one_time_and_pick_two_times() {
        Car car = new Car();

        ParkingLot parkingService = new ParkingLot();
        Ticket ticket = parkingService.park(car).get();

        parkingService.pickup(ticket);
        Optional<Car> actual = parkingService.pickup(ticket);
        assertFalse(actual.isPresent());
    }

    @Test
    public void should_return_optional_empty_when_parking_log_is_full() {

        ParkingLot parkingService = new ParkingLot(1);
        parkingService.park(new Car());
        Optional<Ticket> emptyTicket = parkingService.park(new Car());
        assertFalse(emptyTicket.isPresent());
    }

    @Test
    public void should_failed_when_pickup_car_in_the_wrong_parking_lot() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        Ticket ticket = parkingLotA.park(new Car()).get();
        Optional<Car> actual = parkingLotB.pickup(ticket);
        assertFalse(actual.isPresent());
    }
}
