package hitbox;

/**
 * Represents a collision strategy that checks for collisions between a rectangle and a circle.
 * <p>
 * This class implements the {@link CollisionStrategy} interface and provides the logic to detect if
 * a {@link Rectangle} and a {@link Circle} collide based on their geometric properties.
 * </p>
 */
public class RectangleCircleCollision implements CollisionStrategy {

    /**
     * Checks if a {@link Rectangle} and a {@link Circle} collide.
     * <p>
     * This method calculates the closest point on the rectangle to the circle and checks if the distance
     * between this point and the circle's center is less than or equal to the circle's radius.
     * </p>
     *
     * @param shape1 the first shape, expected to be a {@link Rectangle}
     * @param shape2 the second shape, expected to be a {@link Circle}
     * @return {@code true} if the rectangle and the circle collide, {@code false} otherwise
     * @throws IllegalArgumentException if either shape is not a {@link Rectangle} or {@link Circle}
     */
    @Override
    public boolean collides(Shape shape1, Shape shape2) {
        if (!(shape1 instanceof Rectangle) || !(shape2 instanceof Circle)) {
            throw new IllegalArgumentException("The first shape must be a Rectangle and the second shape must be a Circle.");
        }

        Rectangle rectangle = (Rectangle) shape1;
        Circle circle = (Circle) shape2;

        float rectXMin = rectangle.getX();
        float rectYMin = rectangle.getY();
        float rectXMax = rectangle.getX() + rectangle.getWidth();
        float rectYMax = rectangle.getY() + rectangle.getHeight();

        float closestX = Math.max(rectXMin, Math.min(circle.getX(), rectXMax));
        float closestY = Math.max(rectYMin, Math.min(circle.getY(), rectYMax));

        float distanceX = circle.getX() - closestX;
        float distanceY = circle.getY() - closestY;

        return (distanceX * distanceX + distanceY * distanceY) <= (circle.getRadius() * circle.getRadius());
    }
}
