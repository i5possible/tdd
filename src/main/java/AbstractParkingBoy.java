import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class AbstractParkingBoy {
    protected List<ParkingLot> parkingLots = new ArrayList<>();

    public AbstractParkingBoy() {
        this.parkingLots.add(new ParkingLot());
    }

    public AbstractParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots.addAll(Arrays.asList(parkingLots));
    }

    public Optional<Car> pickup(Ticket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            Optional<Car> car = parkingLot.pickup(ticket);
            if (car.isPresent()) {
                return car;
            }
        }
        return Optional.empty();
    }

    public abstract Optional<Ticket> park(Car car);
}
