package enemyFactory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entity.Entity;
import entity.enemy.BossType2;
import entity.enemy.BossType3;
import mvc.Controller;
import mvc.View;
import mvc.enemyController.BossType2Controller;
import mvc.enemyController.BossType3Controller;
import random.EntityManager;

import java.util.List;

/**
 * A factory class responsible for creating BossType3 entities, their views,
 * and their controllers in a game using the MVC architecture.
 *
 * This class implements the {@link EnemyFactory} interface and provides
 * concrete implementations for creating:
 *   {@link Entity} instances of type {@code BossType3}
 *   instances for {@code BossType3}
 *   {@link Controller} instances to manage the behavior of {@code BossType3}
 */
public class BossType3Factory implements EnemyFactory {

    /**
     * Creates a new {@link BossType3} entity with the specified position, health, and rendering batch.
     *
     * @param X the x-coordinate of the boss's position
     * @param Y the y-coordinate of the boss's position
     * @param health the initial health value of the boss
     * @param batch the {@link SpriteBatch} used for rendering
     * @return a new {@link BossType3} entity
     */
    @Override
    public Entity createEntity(float X, float Y, int health, SpriteBatch batch) {
        return new BossType3(X, Y, health, batch);
    }

    /**
     * Creates a new {@link View} for the given {@link Entity}.
     *
     * @param entity the {@link Entity} for which the view is created
     * @return a new {@link View} instance linked to the specified entity
     */
    @Override
    public View createView(Entity entity) {
        return new View(entity);
    }

    /**
     * Creates a new {@link Controller} to manage the behavior of the specified {@link Entity}.
     *
     * @param entity the {@link Entity} to be controlled
     * @param entityManager the {@link EntityManager} responsible for managing all entities in the game
     * @return a new {@link BossType3Controller} instance to control the specified entity
     */
    @Override
    public Controller createController(Entity entity, EntityManager entityManager) {
        return new BossType3Controller(entity, entityManager);
    }
}

