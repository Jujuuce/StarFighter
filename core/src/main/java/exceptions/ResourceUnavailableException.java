package exceptions;

/**
 * An exception thrown when a requested resource is unavailable.
 *
 * <p>This exception is a specific type of {@link GameException} used to
 * indicate that a resource, such as an asset or file, could not be located
 * or accessed.</p>
 */
public class ResourceUnavailableException extends GameException {

    /**
     * Constructs a new {@code ResourceUnavailableException} with a message
     * indicating the missing resource's path.
     *
     * @param assetPath the path of the resource that could not be found
     */
    public ResourceUnavailableException(String assetPath) {
        super("Resource not found: " + assetPath);
    }
}
