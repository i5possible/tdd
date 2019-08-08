import org.junit.Test;

public class ParkingServiceTest {

    @Test(expected = IllegalTicketException.class)
    public void should_throw_exception_when_pickup_with_invalid_ticket() {
        Ticket invalid = new Ticket("invalid");

        ParkingService parkingService = new ParkingService();
        parkingService.pickup(invalid);
    }
}
