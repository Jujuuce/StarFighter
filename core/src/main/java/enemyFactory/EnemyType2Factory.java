package enemyFactory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entity.*;
import entity.enemy.*;
import mvc.*;
import mvc.enemyController.EnemyType1Controller;
import mvc.enemyController.EnemyType2Controller;
import random.EntityManager;

import java.util.List;

/**
 * A factory class for creating instances of EnemyType2, including its entity,
 * view, and controller components, in a game using the MVC architecture.
 *
 * This factory implements the {@link EnemyFactory} interface and specializes
 * in creating enemies of type "EnemyType2". It provides:
 *
 *   An {@link Entity} representing the data and state of the enemy
 *   A {@link View} for rendering the enemy
 *   A {@link Controller} to manage the enemy's behavior
 */
public class EnemyType2Factory implements EnemyFactory {

    /**
     * Creates an entity representing an EnemyType2 instance.
     *
     * @param X the x-coordinate of the enemy's initial position
     * @param Y the y-coordinate of the enemy's initial position
     * @param health the initial health of the enemy
     * @param batch the {@link SpriteBatch} used for rendering
     * @return an {@link Entity} representing an EnemyType2
     */
    @Override
    public Entity createEntity(float X, float Y, int health, SpriteBatch batch) {
        return new EnemyType2(X, Y, health, batch);
    }

    /**
     * Creates a view for rendering the specified EnemyType2 entity.
     *
     * @param entity the {@link Entity} representing the EnemyType2
     * @return a {@link View} associated with the specified entity
     */
    @Override
    public View createView(Entity entity) {
        return new View(entity);
    }

    /**
     * Creates a controller to manage the behavior of the specified EnemyType2 entity.
     *
     * @param entity the {@link Entity} representing the EnemyType2
     * @param entityManager the {@link EntityManager} responsible for managing all entities in the game
     * @return a {@link Controller} to handle the behavior of the specified entity
     */
    @Override
    public Controller createController(Entity entity, EntityManager entityManager) {
        return new EnemyType2Controller(entity, entityManager);
    }
}
