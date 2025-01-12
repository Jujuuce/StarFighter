package enemyFactory;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entity.Entity;
import mvc.*;
import random.EntityManager;

import java.util.List;

/**
 * An interface defining a factory for creating enemy entities, their views,
 * and their controllers in a game using the MVC architecture.
 *
 * This interface is designed to allow for the creation of different types
 * of enemies by implementing specific factories. Each factory is responsible
 * for producing:
 *   An instance representing the enemy's data and state
 *   A instance responsible for rendering the enemy
 *   A instance for managing the enemy's behavior
 */
public interface EnemyFactory {

    /**
     * Creates a new enemy entity with the specified parameters.
     *
     * @param X the x-coordinate of the enemy's position
     * @param Y the y-coordinate of the enemy's position
     * @param health the initial health value of the enemy
     * @param batch the {@link SpriteBatch} used for rendering
     * @return an {@link Entity} representing the enemy
     */
    Entity createEntity(float X, float Y, int health, SpriteBatch batch);

    /**
     * Creates a view for the specified enemy entity.
     *
     * @param entity the {@link Entity} representing the enemy
     * @return a {@link View} instance linked to the specified entity
     */
    View createView(Entity entity);

    /**
     * Creates a controller to manage the specified enemy entity.
     *
     * @param entity the {@link Entity} to be controlled
     * @param entityManager the {@link EntityManager} responsible for managing all entities in the game
     * @return a {@link Controller} for the specified entity
     */
    Controller createController(Entity entity, EntityManager entityManager);
}
