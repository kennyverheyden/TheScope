package thescope.exceptions;

public class BookingNotFoundException extends RuntimeException {

    public BookingNotFoundException(Long id) {
        super("The booking with id " + id + " could not be found.");
    }
}
