package reservations;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationService {

    public static List<Integer> detectActiveReservations(List<ReservationEntity> entities) {

        HashMap<Integer, Integer> counter = new HashMap<>();

        // Count active reservations per day
        for (ReservationEntity entity : entities) {
            for (int i = entity.getCheckInDate(); i < entity.getCheckoutDate(); i++) {
                counter.merge(i, 1, Integer::sum);
            }
        }

        double average = calculateDesiredDaysAverage(counter, 7);

        List<Integer> daysWithLessActiveReservation = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            int day = entry.getKey();

            if (day > 7 && entry.getValue() < average) {
                daysWithLessActiveReservation.add(day);
            }
        }

        return daysWithLessActiveReservation;
    }

    // Compute 7-day rolling average
    private static double calculateDesiredDaysAverage(HashMap<Integer, Integer> counter, int desiredDays) {
        double sum = 0.0;
        int count = 0;
        for (Integer value : counter.values()) {
            if (count >= desiredDays) break;
            sum += value;
            count++;
        }
        return count > 0 ? sum / count : 0;
    }
}
