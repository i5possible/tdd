import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class ParkingLotTest {

    @Test(expected = IllegalTicketException.class)
    public void should_throw_exception_when_pickup_with_invalid_ticket() {
        Ticket invalid = new Ticket();

        ParkingLot parkingService = new ParkingLot();
        parkingService.pickup(invalid);
    }

    @Test
    public void should_get_different_ticket_when_park_two_cars() {
        Car carA = new Car();
        Car carB = new Car();
        ParkingLot parkingService = new ParkingLot();
        Assert.assertThat(parkingService.park(carA), not(equalTo(parkingService.park(carB))));
    }

    @Test
    public void should_get_the_car_by_ticket() {
        Car expected = new Car();

        ParkingLot parkingService = new ParkingLot();
        Ticket ticket = parkingService.park(expected);
        Car actual = parkingService.pickup(ticket);

        Assert.assertThat(expected, is(actual));
    }

    @Test(expected = IllegalCarException.class)
    public void should_throw_exception_when_park_one_car_two_times() {
        Car car = new Car();

        ParkingLot parkingService = new ParkingLot();
        parkingService.park(car);
        parkingService.park(car);
    }

    @Test
    public void should_park_and_pickup_three_cars() {
        Car carA = new Car();
        Car carB = new Car();
        Car carC = new Car();

        ParkingLot parkingService = new ParkingLot();
        Ticket ticketA = parkingService.park(carA);
        Ticket ticketB = parkingService.park(carB);
        Ticket ticketC = parkingService.park(carC);

        Assert.assertThat(carA, is(parkingService.pickup(ticketA)));
        Assert.assertThat(carB, is(parkingService.pickup(ticketB)));
        Assert.assertThat(carC, is(parkingService.pickup(ticketC)));
    }

    @Test(expected = IllegalTicketException.class)
    public void should_throw_exception_when_park_one_time_and_pick_two_times() {
        Car car = new Car();

        ParkingLot parkingService = new ParkingLot();
        Ticket ticket = parkingService.park(car);

        parkingService.pickup(ticket);
        parkingService.pickup(ticket);
    }
}
