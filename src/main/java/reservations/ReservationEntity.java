package reservations;

public class ReservationEntity {

    private int reservationId;
    private int checkInDate;
    private int checkoutDate;

    public ReservationEntity(int reservationId, int checkInDate, int checkoutDate) {
        this.reservationId = reservationId;
        this.checkInDate = checkInDate;
        this.checkoutDate = checkoutDate;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(int checkInDate) {
        this.checkInDate = checkInDate;
    }

    public int getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(int checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
}
