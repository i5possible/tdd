import java.util.HashMap;
import java.util.Map;

public class ParkingService {

    private Map<Ticket, Car> cars = new HashMap<>();


    public Car pickup(Ticket ticket) {
        if (!cars.containsKey(ticket)) {
          throw new IllegalTicketException();
        }
        return new Car();
    }

    public Ticket park(Car car) {
        return new Ticket();
    }
}
