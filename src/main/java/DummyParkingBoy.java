import java.util.Optional;

public class DummyParkingBoy extends AbstractParkingBoy{

    public DummyParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
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
