package exceptions;

/**
 * A custom exception class for handling game-specific errors.
 *
 * <p>This class serves as a base exception for all custom exceptions in the game.
 * It extends {@link RuntimeException}, allowing for unchecked exceptions
 * that can be used throughout the game's codebase.</p>
 */
public class GameException extends RuntimeException {

    /**
     * Constructs a new {@code GameException} with the specified detail message.
     *
     * @param message the detail message providing more information about the exception
     */
    public GameException(String message) {
        super(message);
    }
}
