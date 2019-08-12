import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class ParkingServiceTest {

    @Test(expected = IllegalTicketException.class)
    public void should_throw_exception_when_pickup_with_invalid_ticket() {
        Ticket invalid = new Ticket();

        ParkingService parkingService = new ParkingService();
        parkingService.pickup(invalid);
    }

    @Test
    public void should_park_car_successfully() {
        Car car = new Car();

        ParkingService parkingService = new ParkingService();
        Ticket ticket = parkingService.park(car);

        Assert.assertThat(ticket, is(car));
    }

    @Test
    public void should_get_different_ticket_when_park_two_cars() {
        Car carA = new Car();
        Car carB = new Car();
        ParkingService parkingService = new ParkingService();
        Assert.assertThat(parkingService.park(carA), not(equalTo(parkingService.park(carB))));
    }
}
