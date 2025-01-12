package hitbox;

/**
 * Represents a collision strategy that checks for collisions between two rectangles.
 * <p>
 * This class implements the {@link CollisionStrategy} interface and provides the logic to detect if
 * two {@link Rectangle} objects collide based on their geometric properties.
 * </p>
 */
public class RectangleRectangleCollision implements CollisionStrategy {

    /**
     * Checks if two {@link Rectangle} objects collide.
     * <p>
     * This method checks if the rectangles overlap by comparing their coordinates and dimensions.
     * The method returns {@code true} if the rectangles are intersecting, and {@code false} otherwise.
     * </p>
     *
     * @param shape1 the first shape, expected to be a {@link Rectangle}
     * @param shape2 the second shape, expected to be a {@link Rectangle}
     * @return {@code true} if the two rectangles collide, {@code false} otherwise
     * @throws IllegalArgumentException if either shape is not a {@link Rectangle}
     */
    @Override
    public boolean collides(Shape shape1, Shape shape2) {
        if (!(shape1 instanceof Rectangle) || !(shape2 instanceof Rectangle)) {
            throw new IllegalArgumentException("Both shapes must be instances of Rectangle.");
        }

        Rectangle r1 = (Rectangle) shape1;
        Rectangle r2 = (Rectangle) shape2;
        float r1X = r1.getX();
        float r1Y = r1.getY();
        float r2X = r2.getX();
        float r2Y = r2.getY();

        // Check if the rectangles overlap horizontally and vertically
        return r1X < r2X + r2.getWidth() &&
            r1X + r1.getWidth() > r2X &&
            r1Y < r2Y + r2.getHeight() &&
            r1Y + r1.getHeight() > r2Y;
    }
}
