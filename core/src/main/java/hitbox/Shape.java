package hitbox;

import com.badlogic.gdx.math.Vector2;

/**
 * Abstract class representing a generic shape with a position and collision strategy.
 * <p>
 * This class provides basic functionality for 2D shapes, including position handling and the ability to
 * check for collisions with other shapes using a specified collision strategy.
 * </p>
 */
public abstract class Shape {
    private Vector2 position;
    public CollisionStrategy collisionStrategy;

    /**
     * Constructs a shape with the specified coordinates.
     *
     * @param X the X-coordinate of the shape
     * @param Y the Y-coordinate of the shape
     */
    public Shape(float X, float Y) {
        setPosition(new Vector2(X, Y));
    }

    /**
     * Constructs a shape with the specified position.
     *
     * @param position the position of the shape as a {@link Vector2}
     */
    public Shape(Vector2 position) {
        setPosition(position);
    }

    /**
     * Sets the collision strategy for this shape.
     *
     * @param strategy the {@link CollisionStrategy} to be used for collision detection
     * @throws IllegalArgumentException if the strategy is {@code null}
     */
    public void setCollisionStrategy(CollisionStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Collision strategy cannot be null.");
        }
        this.collisionStrategy = strategy;
    }

    /**
     * Checks if this shape collides with another shape using the current collision strategy.
     *
     * @param other the other shape to check for collision
     * @return {@code true} if the shapes collide, {@code false} otherwise
     * @throws IllegalStateException if no collision strategy is set for this shape
     * @throws IllegalArgumentException if the other shape is {@code null}
     */
    public boolean overlaps(Shape other) {
        if (collisionStrategy == null) {
            throw new IllegalStateException("No collision strategy set for this shape.");
        }
        if (other == null) {
            throw new IllegalArgumentException("Other shape cannot be null.");
        }
        return collisionStrategy.collides(this, other);
    }

    /**
     * Adds a velocity to the position of this shape.
     *
     * @param velocity the {@link Vector2} velocity to be added to the shape's position
     * @throws IllegalArgumentException if the velocity is {@code null}
     */
    public void add(Vector2 velocity) {
        if (velocity == null) {
            throw new IllegalArgumentException("Velocity cannot be null.");
        }
        position.add(velocity);
    }

    // Getters and setters

    /**
     * Sets the position of the shape.
     *
     * @param position the new position of the shape as a {@link Vector2}
     * @throws IllegalArgumentException if the position is {@code null}
     */
    public void setPosition(Vector2 position) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null.");
        }
        this.position = position;
    }

    /**
     * Sets the X-coordinate of the shape.
     *
     * @param x the X-coordinate of the shape
     */
    public void setX(float x) {
        position.x = x;
    }

    /**
     * Sets the Y-coordinate of the shape.
     *
     * @param y the Y-coordinate of the shape
     */
    public void setY(float y) {
        position.y = y;
    }

    /**
     * Gets the position of the shape.
     *
     * @return the position of the shape as a {@link Vector2}
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Gets the X-coordinate of the shape.
     *
     * @return the X-coordinate of the shape
     */
    public float getX() {
        return position.x;
    }

    /**
     * Gets the Y-coordinate of the shape.
     *
     * @return the Y-coordinate of the shape
     */
    public float getY() {
        return position.y;
    }
}


