import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingBoy {

    public List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy() {
        this.parkingLots.add(new ParkingLot());
    }

    public ParkingBoy(ParkingLot... parkingLots) {
        for (ParkingLot parkingLot : parkingLots) {
            this.parkingLots.add(parkingLot);
        }
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
