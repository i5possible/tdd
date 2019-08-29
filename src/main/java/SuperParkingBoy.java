import java.util.Comparator;
import java.util.Optional;

public class SuperParkingBoy extends AbstractParkingBoy {

    public SuperParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    public Optional<Ticket> park(Car car) {
        Optional<ParkingLot> candidate = parkingLots.stream()
                .sorted(Comparator.comparing(ParkingLot::getVacancyRatio).reversed()
                        .thenComparing(ParkingLot::getCapacity).reversed())
                .filter(ParkingLot::isAvailable)
                .findFirst();

        return candidate.isPresent() ? candidate.get().park(car) : Optional.empty();
    }
}
