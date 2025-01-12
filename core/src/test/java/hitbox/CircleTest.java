package hitbox;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CircleTest {

    @Test
    public void testConstructorWithNegativeRadius() {
        // Tester le constructeur avec un rayon négatif
        assertThrows(IllegalArgumentException.class, () -> new Circle(0, 0, -1));
    }

    @Test
    public void testSetRadiusWithNegativeRadius() {
        Circle circle = new Circle(0, 0, 10);
        // Tester setRadius avec un rayon négatif
        assertThrows(IllegalArgumentException.class, () -> circle.setRadius(-5));
    }

    @Test
    public void testValidCircle() {
        // Tester un cercle valide avec un rayon positif
        Circle circle = new Circle(0, 0, 10);
        assertEquals(10, circle.getRadius());
    }

    @Test
    public void testSetRadiusWithValidRadius() {
        Circle circle = new Circle(0, 0, 10);
        circle.setRadius(15);
        assertEquals(15, circle.getRadius());
    }
}
