import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DummyParkingBoyTest {

    @Test
    public void should_park_car_according_to_the_order() {
        int initCapacity = 3;
        ParkingLot parkingLotA = new ParkingLot(initCapacity);
        ParkingLot parkingLotB = new ParkingLot(initCapacity);
        DummyParkingBoy dummyParkingBoy = new DummyParkingBoy(parkingLotA, parkingLotB);
        dummyParkingBoy.park(new Car());
        dummyParkingBoy.park(new Car());
        dummyParkingBoy.park(new Car());
        assertThat(parkingLotB.getCapacity(), is(initCapacity));
    }
}
