package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    private Entity entity;

    @BeforeEach
    public void setUp() {
        // Création d'une entité avec des valeurs par défaut pour le test
        entity = new Entity();  // Position (50, 50), santé initiale de 100
    }

    @Test
    public void testSetHealthValid() {
        // Test si la santé est bien définie
        entity.setHealth(150);
        assertEquals(150, entity.getHealth(), "Health should be 150.");
    }

    @Test
    public void testSetHealthInvalid() {
        // Test si la santé ne peut pas être définie à une valeur inférieure ou égale à zéro
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            entity.setHealth(-10);  // Tentative de définir une santé invalide
        });
        assertEquals("Health must be greater than zero.", exception.getMessage());
    }

    @Test
    public void testTakeDamageNegative() {
        // Test si l'exception est levée lorsqu'on tente d'appliquer des dégâts négatifs
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            entity.takeDamage(-10);  // Tentative d'infliger des dégâts négatifs
        });
        assertEquals("Damage cannot be negative.", exception.getMessage());
    }
}
