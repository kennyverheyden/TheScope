package thescope.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with id: " + id + "doesn't exists in the database.");
    }
}
