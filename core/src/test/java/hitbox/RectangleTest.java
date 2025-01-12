package hitbox;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RectangleTest {

    @Test
    public void testConstructorWithNegativeWidth() {
        // Tester le constructeur avec une largeur négative
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 0, -1, 10));
    }

    @Test
    public void testConstructorWithNegativeHeight() {
        // Tester le constructeur avec une hauteur négative
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 0, 10, -1));
    }

    @Test
    public void testConstructorWithNegativeWidthAndHeight() {
        // Tester le constructeur avec largeur et hauteur négatives
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 0, -1, -1));
    }

    @Test
    public void testSetWidthWithNegativeWidth() {
        Rectangle rectangle = new Rectangle(0, 0, 10, 10);
        // Tester setWidth avec une largeur négative
        assertThrows(IllegalArgumentException.class, () -> rectangle.setWidth(-5));
    }

    @Test
    public void testSetHeightWithNegativeHeight() {
        Rectangle rectangle = new Rectangle(0, 0, 10, 10);
        // Tester setHeight avec une hauteur négative
        assertThrows(IllegalArgumentException.class, () -> rectangle.setHeight(-5));
    }

    @Test
    public void testValidRectangle() {
        // Tester un rectangle valide avec des dimensions positives
        Rectangle rectangle = new Rectangle(0, 0, 10, 20);
        assertEquals(10, rectangle.getWidth());
        assertEquals(20, rectangle.getHeight());
    }

    @Test
    public void testSetWidthWithValidWidth() {
        Rectangle rectangle = new Rectangle(0, 0, 10, 10);
        rectangle.setWidth(15);
        assertEquals(15, rectangle.getWidth());
    }

    @Test
    public void testSetHeightWithValidHeight() {
        Rectangle rectangle = new Rectangle(0, 0, 10, 10);
        rectangle.setHeight(25);
        assertEquals(25, rectangle.getHeight());
    }
}
