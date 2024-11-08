package multithreading;

public class DataRaceException extends Exception {
    private final String message;

    public DataRaceException(String message) {
        super(message); // Call superclass constructor to store the message
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

