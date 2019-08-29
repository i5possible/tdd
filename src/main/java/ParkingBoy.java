import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;
    private ParkingSelector parkingSelector;

    public ParkingBoy(List<ParkingLot> parkingLots, ParkingSelector parkingSelector) {
        this.parkingLots = new ArrayList<>(parkingLots);
        this.parkingSelector = parkingSelector;
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
        return parkingSelector.selectParkingLog(parkingLots).flatMap(parkingLot -> parkingLot.park(car));
    }
}
