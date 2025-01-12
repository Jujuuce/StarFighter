package hitbox;

import com.badlogic.gdx.math.Vector2;

/**
 * Represents a circle shape defined by its position and radius.
 * <p>
 * This class extends the {@link Shape} class and provides functionality to define and manipulate a circle.
 * It allows setting and retrieving the radius, as well as validating the radius to ensure it is non-negative.
 * </p>
 */
public class Circle extends Shape {
    private float radius;

    /**
     * Creates a new circle with the specified position and radius.
     *
     * @param X the X coordinate of the circle's position
     * @param Y the Y coordinate of the circle's position
     * @param radius the radius of the circle
     * @throws IllegalArgumentException if the radius is negative
     */
    public Circle(float X, float Y, float radius) {
        super(X, Y);
        setRadius(radius);
    }

    /**
     * Creates a new circle with the specified position and radius.
     *
     * @param position the position of the circle
     * @param radius the radius of the circle
     * @throws IllegalArgumentException if the radius is negative
     */
    public Circle(Vector2 position, float radius) {
        super(position);
        setRadius(radius);
    }

    /**
     * Sets the radius of the circle.
     *
     * @param radius the radius to set
     * @throws IllegalArgumentException if the radius is negative
     */
    public void setRadius(float radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative.");
        }
        this.radius = radius;
    }

    /**
     * Gets the radius of the circle.
     *
     * @return the radius of the circle
     */
    public float getRadius() {
        return radius;
    }
}
