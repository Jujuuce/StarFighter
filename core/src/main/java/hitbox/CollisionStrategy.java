package hitbox;

/**
 * Represents a strategy for detecting collisions between two shapes.
 * <p>
 * This interface provides a method to check whether two given shapes collide. Implementations
 * of this interface define the specific logic for collision detection based on the shapes' types and properties.
 * </p>
 */
public interface CollisionStrategy {

    /**
     * Checks if two shapes collide.
     *
     * @param shape1 the first shape to check for collision
     * @param shape2 the second shape to check for collision
     * @return {@code true} if the two shapes collide, {@code false} otherwise
     */
    boolean collides(Shape shape1, Shape shape2);
}
