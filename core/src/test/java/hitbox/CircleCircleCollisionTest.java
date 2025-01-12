package hitbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CircleCircleCollisionTest {

    @Test
    public void testCollidesWithNonCircleShapes() {
        // Tester avec des formes autres que des cercles
        Shape rectangle = new Rectangle(0, 0, 10, 10);
        Shape circle = new Circle(0, 0, 10);

        CollisionStrategy collisionStrategy = new CircleCircleCollision();

        // Vérifie que l'exception est lancée lorsque les objets ne sont pas des cercles
        assertThrows(IllegalArgumentException.class, () -> collisionStrategy.collides(rectangle, circle));
    }

    @Test
    public void testCollidesWithCircles() {
        // Tester avec deux cercles qui se chevauchent
        Circle c1 = new Circle(0, 0, 10);
        Circle c2 = new Circle(15, 0, 10);

        CollisionStrategy collisionStrategy = new CircleCircleCollision();

        assertTrue(collisionStrategy.collides(c1, c2)); // Les cercles se chevauchent
    }

    @Test
    public void testNoCollisionWithCircles() {
        // Tester avec deux cercles qui ne se chevauchent pas
        Circle c1 = new Circle(0, 0, 10);
        Circle c2 = new Circle(30, 0, 10);

        CollisionStrategy collisionStrategy = new CircleCircleCollision();

        assertFalse(collisionStrategy.collides(c1, c2)); // Pas de chevauchement
    }
}

