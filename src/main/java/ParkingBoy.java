public class ParkingBoy {

    public ParkingLot parkingLot= new ParkingLot();

    public Car pickup(Ticket ticket) {
        return parkingLot.pickup(ticket);
    }

    public Ticket park(Car car) {
        return parkingLot.park(car);
    }
}
