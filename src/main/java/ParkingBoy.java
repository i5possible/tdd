import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    public List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy() {
        new ParkingBoy(new ParkingLot());
    }

    public ParkingBoy(ParkingLot... parkingLots) {
        for (ParkingLot parkingLot : parkingLots) {
            this.parkingLots.add(parkingLot);
        }
    }

    public Car pickup(Ticket ticket) {
        return parkingLots.get(0).pickup(ticket);
    }

    public Ticket park(Car car) {
        return parkingLots.get(0).park(car);
    }


}
