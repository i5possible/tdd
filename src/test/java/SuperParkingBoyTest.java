import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SuperParkingBoyTest {

    @Test
    public void should_park_car_to_parking_lotB_when_parking_lotB_has_more_vacant_ratio() {
        ParkingLot parkingLotA = new ParkingLot(3);
        ParkingLot parkingLotB = new ParkingLot(2);
        parkingLotA.park(new Car());
        parkingLotA.park(new Car());
        parkingLotB.park(new Car());
        SuperParkingBoy smartParkingBoy = new SuperParkingBoy(parkingLotA, parkingLotB);
        smartParkingBoy.park(new Car());
        assertThat(parkingLotA.getCapacity(), is(1));
        assertThat(parkingLotB.getCapacity(), is(0));
    }

    @Test
    public void should_park_car_to_parking_lotB_when_has_same_vacant_ratio_and_lotB_has_more_available_capacity() {
        ParkingLot parkingLotA = new ParkingLot(4);
        ParkingLot parkingLotB = new ParkingLot(6);
        parkingLotA.park(new Car());
        parkingLotA.park(new Car());
        parkingLotB.park(new Car());
        parkingLotB.park(new Car());
        parkingLotB.park(new Car());
        SuperParkingBoy smartParkingBoy = new SuperParkingBoy(parkingLotA, parkingLotB);
        smartParkingBoy.park(new Car());
        assertThat(parkingLotA.getCapacity(), is(2));
        assertThat(parkingLotB.getCapacity(), is(2));
    }


    @Test
    public void should_park_car_to_parking_lotA_when_has_same_vacant_ratio_and_capacity() {
        ParkingLot parkingLotA = new ParkingLot(6);
        ParkingLot parkingLotB = new ParkingLot(6);
        parkingLotA.park(new Car());
        parkingLotA.park(new Car());
        parkingLotA.park(new Car());
        parkingLotB.park(new Car());
        parkingLotB.park(new Car());
        parkingLotB.park(new Car());
        SuperParkingBoy smartParkingBoy = new SuperParkingBoy(parkingLotA, parkingLotB);
        smartParkingBoy.park(new Car());
        assertThat(parkingLotA.getCapacity(), is(2));
        assertThat(parkingLotB.getCapacity(), is(3));
    }
}
