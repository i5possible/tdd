import java.util.HashMap;
import java.util.Map;

public class ParkingService {

    private Map<Ticket, Car> cars = new HashMap<>();


    public Car pickup(Ticket ticket) {
        if (!cars.containsKey(ticket)) {
          throw new IllegalTicketException();
        }
        Car car = cars.get(ticket);
        cars.remove(ticket);
        return car;
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        if (cars.containsValue(car)) {
            throw new IllegalCarException();
        }
        cars.put(ticket, car);
        return ticket;

    }
}
