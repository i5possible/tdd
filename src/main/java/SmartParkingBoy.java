import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoy {

    private List<ParkingLot> parkingLots = new ArrayList<>();

    public SmartParkingBoy() {
        this.parkingLots.add(new ParkingLot());
    }

    public SmartParkingBoy(ParkingLot... parkingLots) {
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
        Optional<ParkingLot> candidate = parkingLots.stream()
                .sorted((parkingLotA, parkingLotB) -> parkingLotB.getCapacity() - parkingLotA.getCapacity())
                .filter(ParkingLot::isAvailable)
                .findFirst();

        return candidate.isPresent() ? candidate.get().park(car) : Optional.empty();
    }


}
