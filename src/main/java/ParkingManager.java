import java.util.List;
import java.util.Optional;

public class ParkingManager {

    private List<ParkingLot> parkingLots;
    private List<ParkingBoy> parkingBoys;

    public ParkingManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        this.parkingLots = parkingLots;
        this.parkingBoys = parkingBoys;
    }

    public Optional<Ticket> park(Car car) {
        Optional<Ticket> ticket = parkingBoys.stream().filter(ParkingBoy::isAvailable).findAny()
                .flatMap(parkingBoy -> parkingBoy.park(car));
        if (ticket.isPresent()) {
            return ticket;
        } else {
            return parkingLots.stream().filter(ParkingLot::isAvailable).findAny()
                    .flatMap(parkingLot -> parkingLot.park(car));
        }
    }
}
