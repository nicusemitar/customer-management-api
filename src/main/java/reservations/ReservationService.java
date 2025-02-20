package reservations;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationService {

    public static List<Integer> detectActiveReservations(List<ReservationEntity> entities) {

        HashMap<Integer, Integer> counter = new HashMap<>();

        for (ReservationEntity entity : entities) {
            int checkinDate = entity.getCheckInDate() == 0 ? entity.getCheckInDate() + 1 : entity.getCheckInDate();

            for (int i = checkinDate; i <= entity.getCheckoutDate() - 1; i++) {
                if (counter.containsKey(i)) {
                    counter.put(i, counter.get(i) + 1);
                    //put if absent ?
                } else {
                    counter.put(i, 1);
                }
            }

        }
        double sum = 0.0;
        int count = 0;
        for (Integer value : counter.values()) {
            if (count >= 7) break;
            sum += value;
            count++;
        }
        double average = count > 0 ? sum / count : 0;

        List<Integer> activeReservation = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getKey() > 7) {
                boolean isLess = entry.getValue() < average;
                if (isLess) {
                    activeReservation.add(entry.getKey());
                }
            }
        }

        return activeReservation;
    }
}
