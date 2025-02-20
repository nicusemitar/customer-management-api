package reservations;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ReservationEntity r1 = new ReservationEntity(123, 0, 6);
        ReservationEntity r2 = new ReservationEntity(435, 3, 7);
        ReservationEntity r3 = new ReservationEntity(444, 2, 13);
        ReservationEntity r4 = new ReservationEntity(532, 5, 9);
        ReservationEntity r5 = new ReservationEntity(232, 9, 12);
        ReservationEntity r6 = new ReservationEntity(221, 10, 12);

        List<ReservationEntity> reservationEntityList = Arrays.asList(r1, r2, r3, r4, r5, r6);

        ReservationService.detectActiveReservations(reservationEntityList);

    }
}
