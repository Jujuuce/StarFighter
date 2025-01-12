package hitbox;

import com.badlogic.gdx.math.Vector2;

/**
 * Represents a rectangle shape defined by its position and dimensions (width and height).
 * <p>
 * This class extends the {@link Shape} class and provides functionality to define and manipulate a rectangle.
 * It allows setting and retrieving the width and height, as well as validating the dimensions to ensure they are non-negative.
 * </p>
 */
public class Rectangle extends Shape {
    private float width;
    private float height;

    /**
     * Creates a new rectangle with the specified position, width, and height.
     *
     * @param X the X coordinate of the rectangle's position
     * @param Y the Y coordinate of the rectangle's position
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @throws IllegalArgumentException if the width or height is negative
     */
    public Rectangle(float X, float Y, float width, float height) {
        super(X, Y);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Creates a new rectangle with the specified position, width, and height.
     *
     * @param position the position of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @throws IllegalArgumentException if the width or height is negative
     */
    public Rectangle(Vector2 position, float width, float height) {
        super(position);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Sets the width of the rectangle.
     *
     * @param width the width to set
     * @throws IllegalArgumentException if the width is negative
     */
    public void setWidth(float width) {
        if (width < 0) {
            throw new IllegalArgumentException("Width cannot be negative.");
        }
        this.width = width;
    }

    /**
     * Sets the height of the rectangle.
     *
     * @param height the height to set
     * @throws IllegalArgumentException if the height is negative
     */
    public void setHeight(float height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height cannot be negative.");
        }
        this.height = height;
    }

    /**
     * Gets the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public float getWidth() {
        return width;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public float getHeight() {
        return height;
    }
}

