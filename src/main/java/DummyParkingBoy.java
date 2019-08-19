import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DummyParkingBoy {

    private List<ParkingLot> parkingLots = new ArrayList<>();

    public DummyParkingBoy() {
        this.parkingLots.add(new ParkingLot());
    }

    public DummyParkingBoy(ParkingLot... parkingLots) {
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

    public Optional<Ticket> park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            Optional<Ticket> ticket = parkingLot.park(car);
            if (ticket.isPresent()) {
                return ticket;
            }
        }
        return Optional.empty();
    }


}
