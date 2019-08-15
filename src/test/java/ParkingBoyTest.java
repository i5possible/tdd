import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class ParkingBoyTest {
    @Test(expected = IllegalTicketException.class)
    public void should_throw_exception_when_pickup_with_invalid_ticket() {
        Ticket invalid = new Ticket();

        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.pickup(invalid);
    }

    @Test
    public void should_get_different_ticket_when_park_two_cars() {
        Car carA = new Car();
        Car carB = new Car();
        ParkingBoy parkingBoy = new ParkingBoy();
        Assert.assertThat(parkingBoy.park(carA), not(equalTo(parkingBoy.park(carB))));
    }

    @Test
    public void should_get_the_car_by_ticket() {
        Car expected = new Car();

        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticket = parkingBoy.park(expected);
        Car actual = parkingBoy.pickup(ticket);

        Assert.assertThat(expected, is(actual));
    }

    @Test(expected = IllegalCarException.class)
    public void should_throw_exception_when_park_one_car_two_times() {
        Car car = new Car();

        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.park(car);
        parkingBoy.park(car);
    }

    @Test
    public void should_park_and_pickup_three_cars() {
        Car carA = new Car();
        Car carB = new Car();
        Car carC = new Car();

        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticketA = parkingBoy.park(carA);
        Ticket ticketB = parkingBoy.park(carB);
        Ticket ticketC = parkingBoy.park(carC);

        Assert.assertThat(carA, is(parkingBoy.pickup(ticketA)));
        Assert.assertThat(carB, is(parkingBoy.pickup(ticketB)));
        Assert.assertThat(carC, is(parkingBoy.pickup(ticketC)));
    }

    @Test(expected = IllegalTicketException.class)
    public void should_throw_exception_when_park_one_time_and_pick_two_times() {
        Car car = new Car();

        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticket = parkingBoy.park(car);

        parkingBoy.pickup(ticket);
        parkingBoy.pickup(ticket);
    }

    @Test(expected = ParkingLotFullException.class)
    public void should_throw_exception_when_parking_lots_are_full() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(2), new ParkingLot(3));
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
    }
}
