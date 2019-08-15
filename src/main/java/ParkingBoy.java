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

    public Car pickup(Ticket ticket) {
        return parkingLots.get(0).pickup(ticket);
    }

    public Optional<Ticket> park(Car car) {
        return parkingLots.get(0).park(car);
    }


}
