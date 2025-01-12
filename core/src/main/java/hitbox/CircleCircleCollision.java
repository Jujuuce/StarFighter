package hitbox;

/**
 * A {@link CollisionStrategy} implementation for detecting collisions between two circles.
 * <p>
 * This class provides the logic for detecting whether two {@link Circle} objects intersect.
 * It checks the distance between the centers of the two circles and compares it with the sum of their radii.
 * </p>
 */
public class CircleCircleCollision implements CollisionStrategy {

    /**
     * Checks if two circles collide.
     * <p>
     * The method compares the distance between the centers of the two circles with the sum of their radii.
     * If the distance is less than the sum of the radii, the circles are considered to be colliding.
     * </p>
     *
     * @param shape1 the first shape to check for collision
     * @param shape2 the second shape to check for collision
     * @return {@code true} if the two circles collide, {@code false} otherwise
     * @throws IllegalArgumentException if either of the shapes is not an instance of {@link Circle}
     */
    @Override
    public boolean collides(Shape shape1, Shape shape2) {
        if (!(shape1 instanceof Circle) || !(shape2 instanceof Circle)) {
            throw new IllegalArgumentException("Both shapes must be instances of Circle.");
        }

        Circle c1 = (Circle) shape1;
        Circle c2 = (Circle) shape2;
        float c1X = c1.getX();
        float c1Y = c1.getY();
        float c2X = c2.getX();
        float c2Y = c2.getY();

        float dx = c1X - c2X;
        float dy = c1Y - c2Y;
        float distanceSquared = dx * dx + dy * dy;
        float radiusSum = c1.getRadius() + c2.getRadius();

        return distanceSquared < radiusSum * radiusSum;
    }
}

