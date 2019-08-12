import java.util.HashMap;
import java.util.Map;

public class ParkingService {

    private Map<Ticket, Car> cars = new HashMap<>();


    public Car pickup(Ticket ticket) {
        if (!cars.containsKey(ticket)) {
          throw new IllegalTicketException();
        }
        return cars.get(ticket);
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        cars.put(ticket, car);
        return ticket;
    }
}
