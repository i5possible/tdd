import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

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
}
