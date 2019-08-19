import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SmartParkingBoyTest {

    @Test
    public void should_park_car_to_parking_lotB_when_parking_lotB_remains_more_capacity() {
        int initCapacity = 3;
        ParkingLot parkingLotA = new ParkingLot(initCapacity);
        ParkingLot parkingLotB = new ParkingLot(initCapacity);
        parkingLotA.park(new Car());
        parkingLotA.park(new Car());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotA, parkingLotB);
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        assertThat(parkingLotA.getCapacity(), is(1));
        assertThat(parkingLotB.getCapacity(), is(1));
    }

    @Test
    public void should_park_car_to_parking_lotA_when_parking_lotA_remains_more_capacity() {
        int initCapacity = 3;
        ParkingLot parkingLotA = new ParkingLot(initCapacity);
        ParkingLot parkingLotB = new ParkingLot(initCapacity);
        parkingLotB.park(new Car());
        parkingLotB.park(new Car());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotA, parkingLotB);
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        assertThat(parkingLotB.getCapacity(), is(1));
        assertThat(parkingLotA.getCapacity(), is(1));
    }

    @Test
    public void should_park_car_to_parking_lotA_when_parking_lotA_and_parking_lotB_has_the_same_capacity() {
        int initCapacity = 3;
        ParkingLot parkingLotA = new ParkingLot(initCapacity);
        ParkingLot parkingLotB = new ParkingLot(initCapacity);
        parkingLotA.park(new Car());
        parkingLotA.park(new Car());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotA, parkingLotB);
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        assertThat(parkingLotA.getCapacity(), is(0));
        assertThat(parkingLotB.getCapacity(), is(1));
    }
}
