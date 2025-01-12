package hitbox;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RectangleRectangleCollisionTest {

    @Test
    public void testCollidesWithNonRectangleShapes() {
        // Tester avec des formes autres que des rectangles
        Shape circle = new Circle(0, 0, 10);
        Shape rectangle = new Rectangle(0, 0, 10, 10);

        CollisionStrategy collisionStrategy = new RectangleRectangleCollision();

        // Vérifie que l'exception est lancée lorsque les objets ne sont pas des rectangles
        assertThrows(IllegalArgumentException.class, () -> collisionStrategy.collides(circle, rectangle));
    }

    @Test
    public void testCollidesWithRectangles() {
        // Tester avec deux rectangles qui se chevauchent
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(5, 5, 10, 10);

        CollisionStrategy collisionStrategy = new RectangleRectangleCollision();

        assertTrue(collisionStrategy.collides(r1, r2)); // Les rectangles se chevauchent
    }

    @Test
    public void testNoCollisionWithRectangles() {
        // Tester avec deux rectangles qui ne se chevauchent pas
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(15, 15, 10, 10);

        CollisionStrategy collisionStrategy = new RectangleRectangleCollision();

        assertFalse(collisionStrategy.collides(r1, r2)); // Pas de chevauchement
    }
}
