package hitbox;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RectangleCircleCollisionTest {

    @Test
    public void testCollidesWithRectangleAndCircle() {
        // Test avec un rectangle et un cercle qui se chevauchent
        Rectangle rectangle = new Rectangle(0, 0, 10, 10);
        Circle circle = new Circle(5, 5, 5);

        CollisionStrategy collisionStrategy = new RectangleCircleCollision();

        assertTrue(collisionStrategy.collides(rectangle, circle)); // Les formes se chevauchent
    }

    @Test
    public void testNoCollisionWithRectangleAndCircle() {
        // Test avec un rectangle et un cercle qui ne se chevauchent pas
        Rectangle rectangle = new Rectangle(0, 0, 10, 10);
        Circle circle = new Circle(20, 20, 5);

        CollisionStrategy collisionStrategy = new RectangleCircleCollision();

        assertFalse(collisionStrategy.collides(rectangle, circle)); // Pas de chevauchement
    }

    @Test
    public void testCircleInsideRectangle() {
        // Test avec un cercle complètement à l'intérieur du rectangle
        Rectangle rectangle = new Rectangle(0, 0, 10, 10);
        Circle circle = new Circle(5, 5, 3);

        CollisionStrategy collisionStrategy = new RectangleCircleCollision();

        assertTrue(collisionStrategy.collides(rectangle, circle)); // Le cercle est à l'intérieur du rectangle
    }

    @Test
    public void testCircleTouchingRectangleEdge() {
        // Test avec un cercle touchant le bord du rectangle
        Rectangle rectangle = new Rectangle(0, 0, 10, 10);
        Circle circle = new Circle(10, 5, 5);

        CollisionStrategy collisionStrategy = new RectangleCircleCollision();

        assertTrue(collisionStrategy.collides(rectangle, circle)); // Le cercle touche le bord du rectangle
    }
}
