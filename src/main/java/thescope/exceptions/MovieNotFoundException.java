package thescope.exceptions;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException(Long id) {

        super("The movie with id : '" + id + "' could not be found");
    }
}
