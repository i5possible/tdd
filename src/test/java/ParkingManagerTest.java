import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParkingManagerTest {

    ParkingLot privateParkingLot;
    ParkingBoy parkingBoy;
    ParkingManager parkingManager;

    @Before
    public void setUp() throws Exception {
        privateParkingLot = new ParkingLot(1);
        parkingBoy = new ParkingBoy(Arrays.asList(new ParkingLot(1), new ParkingLot(1)),
                (parkingLots -> parkingLots.stream().filter(ParkingLot::isAvailable).findFirst()));
        parkingManager = new ParkingManager(Collections.singletonList(privateParkingLot),
                Collections.singletonList(parkingBoy));
    }

    @Test
    public void shouldDelegateToParkingBoysIfTheyCanParkCar() {
        Optional<Ticket> ticket = parkingManager.park(new Car());

        assertThat(privateParkingLot.getCapacity(), is(1));
        assertThat(ticket.isPresent(), is(true));
    }

    @Test
    public void shouldParkToPrivateParkingLotIfParkingBoysIfTheyCanNotParkCar() {
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        Optional<Ticket> ticket = parkingManager.park(new Car());
        assertThat(privateParkingLot.getCapacity(), is(0));
        assertThat(ticket.isPresent(), is(true));
    }

    @Test
    public void shouldFailedToParkCarIfThePrivateParkingLotIsFullAndParkingBoysCanNotParkCar() {
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        privateParkingLot.park(new Car());
        Optional<Ticket> ticket = parkingManager.park(new Car());
        assertThat(privateParkingLot.getCapacity(), is(0));
        assertThat(ticket.isPresent(), is(false));
    }
}
