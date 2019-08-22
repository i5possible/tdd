import java.util.Optional;

public class SuperParkingBoy extends AbstractParkingBoy{

    public SuperParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    public Optional<Ticket> park(Car car) {
        Optional<ParkingLot> candidate = parkingLots.stream()
                .sorted((parkingLotA, parkingLotB) -> {
                    if (parkingLotB.getVacancyRatio() > parkingLotA.getVacancyRatio()) {
                        return 1;
                    } else if (parkingLotB.getVacancyRatio() < parkingLotA.getVacancyRatio()) {
                        return -1;
                    } else {
                        return parkingLotB.getCapacity() - parkingLotA.getCapacity();
                    }

                })
                .filter(ParkingLot::isAvailable)
                .findFirst();

        return candidate.isPresent() ? candidate.get().park(car) : Optional.empty();
    }
}
