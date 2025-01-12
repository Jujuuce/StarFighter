package exceptions;

/**
 * A custom exception class for handling cases where a specified game asset
 * cannot be found.
 *
 * <p>This exception is a subclass of {@link GameException} and is specifically
 * designed to indicate errors related to missing assets in the game's resource
 * management system.</p>
 */
public class AssetNotFoundException extends GameException {

    /**
     * Constructs a new {@code AssetNotFoundException} with a detailed message
     * including the path of the missing asset.
     *
     * @param assetPath the path of the asset that could not be found
     */
    public AssetNotFoundException(String assetPath) {
        super("Asset not found: " + assetPath);
    }
}
