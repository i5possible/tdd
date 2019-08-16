import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingLot {

    private Map<Ticket, Car> cars = new HashMap<>();
    private int capacity;

    private static final int DEFAULT_CAPACITY = 10;

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Optional<Car> pickup(Ticket ticket) {
        if (!cars.containsKey(ticket)) {
          return Optional.empty();
        }
        Car car = cars.get(ticket);
        cars.remove(ticket);
        return Optional.of(car);
    }

    public Optional<Ticket> park(Car car) {
        Ticket ticket = new Ticket();
        if (cars.containsValue(car) || capacity <= cars.size()) {
            return Optional.empty();
        }
        cars.put(ticket, car);
        return Optional.of(ticket);

    }

    public int getCapacity() {
        return capacity - cars.size();
    }
}
