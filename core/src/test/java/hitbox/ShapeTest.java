package hitbox;

import com.badlogic.gdx.math.Vector2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ShapeTest {


    @Test
    public void testSetCollisionStrategyWithNullStrategy() {
        Shape shape = new Circle(0, 0, 10);
        // Tester l'assignation d'une stratégie de collision nulle
        assertThrows(IllegalArgumentException.class, () -> shape.setCollisionStrategy(null));
    }

    @Test
    public void testOverlapsWithNullShape() {
        Shape shape = new Circle(0, 0, 10);
        shape.setCollisionStrategy(new RectangleCircleCollision());
        // Tester le cas où 'other' est null dans overlaps
        assertThrows(IllegalArgumentException.class, () -> shape.overlaps(null));
    }

    @Test
    public void testAddWithNullVelocity() {
        Shape shape = new Circle(0, 0, 10);
        // Tester l'ajout d'une vélocité nulle
        assertThrows(IllegalArgumentException.class, () -> shape.add(null));
    }

    @Test
    public void testSetPositionWithNullPosition() {
        Shape shape = new Circle(0, 0, 10);
        // Tester un setPosition avec une position nulle
        assertThrows(IllegalArgumentException.class, () -> shape.setPosition(null));
    }


    // Tu peux aussi tester les comportements valides, par exemple :
    @Test
    public void testValidPosition() {
        Shape shape = new Circle(10, 10, 10);
        assertEquals(10, shape.getX());
        assertEquals(10, shape.getY());
    }
}

