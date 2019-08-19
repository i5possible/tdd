import java.util.Optional;

public class SmartParkingBoy extends AbstractParkingBoy{

    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    public Optional<Ticket> park(Car car) {
        Optional<ParkingLot> candidate = parkingLots.stream()
                .sorted((parkingLotA, parkingLotB) -> parkingLotB.getCapacity() - parkingLotA.getCapacity())
                .filter(ParkingLot::isAvailable)
                .findFirst();

        return candidate.isPresent() ? candidate.get().park(car) : Optional.empty();
    }
}
