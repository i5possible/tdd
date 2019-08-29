import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface ParkingSelector {
    Optional<ParkingLot> selectParkingLog(List<ParkingLot> parkingLotList);
}
