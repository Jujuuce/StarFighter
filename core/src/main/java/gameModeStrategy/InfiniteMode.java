package gameModeStrategy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import enemyFactory.EnemyFactory;
import enemyFactory.EnemyType1Factory;
import enemyFactory.EnemyType2Factory;
import entity.Entity;
import exceptions.GameException;
import game.StarFighter;
import mvc.Controller;
import mvc.MapModel;
import mvc.View;
import random.EntityManager;

import java.util.HashMap;

/**
 * Represents the Infinite game mode for the StarFighter game.
 *
 * <p>In this mode, enemies continuously spawn at random positions and the spawn interval
 * decreases over time, creating an increasingly challenging game.</p>
 */
public class InfiniteMode implements GameMode {

    /** The factories responsible for creating different types of enemies. */
    private HashMap<Integer, EnemyFactory> factories;

    /** The time elapsed since the last enemy spawn. */
    private float spawnTimer;

    /** The current interval between two consecutive spawns. */
    private float spawnInterval;

    /** The minimum spawn interval that the game can reach. */
    private final float minSpawnInterval = 1.0f;

    /** The rate at which the spawn interval decreases after each spawn. */
    private final float spawnDecreaseRate = 0.1f;

    /**
     * Constructs the InfiniteMode with initial spawn settings and enemy factories.
     */
    public InfiniteMode() {
        factories = new HashMap<>();
        factories.put(1, new EnemyType1Factory());
        factories.put(2, new EnemyType2Factory());

        spawnTimer = 0f; // Initial spawn timer
        spawnInterval = 5f; // Initial spawn interval
    }

    /**
     * Handles the spawning of enemies in the Infinite game mode.
     * <p>
     * Enemies are spawned at random positions on the screen. The spawn interval decreases
     * after each spawn, becoming shorter over time. The interval is capped at a minimum value.
     * </p>
     *
     * @param lists    the {@link EntityManager} managing the entities in the game
     * @param mapModel the {@link MapModel} containing the map's data (not used in this mode)
     * @param game     the {@link StarFighter} game instance
     * @throws GameException if the spawn interval or decrease rate is non-positive
     */
    @Override
    public void handleSpawning(EntityManager lists, MapModel mapModel, StarFighter game) {
        spawnTimer += Gdx.graphics.getDeltaTime(); // Update spawn timer with the time passed since the last frame

        if (spawnInterval < 0 || spawnDecreaseRate < 0) {
            throw new GameException("Spawn interval and decrease rate must be positive values.");
        }

        // Spawn an enemy if the time elapsed exceeds the spawn interval
        if (spawnTimer >= spawnInterval) {
            spawnTimer = 0f; // Reset spawn timer

            // Reduce the spawn interval (without going below the minimum value)
            spawnInterval = Math.max(minSpawnInterval, spawnInterval - spawnDecreaseRate);

            // Generate a random enemy
            float x = MathUtils.random(0, Gdx.graphics.getWidth()); // Random horizontal position
            float y = Gdx.graphics.getHeight() + 100f; // Position off-screen, above

            // Choose a random enemy type
            EnemyFactory factory = factories.get(MathUtils.random(1, factories.size()));

            // Create the enemy and its components
            Entity enemy = factory.createEntity(x, y, 10, game.getVisuals().getBatch());
            View view = factory.createView(enemy);
            Controller controller = factory.createController(enemy, lists);

            // Add the enemy to the entity lists
            lists.addEnemy(enemy);
            lists.addController(controller);
        }
    }

    /**
     * Indicates whether this game mode is infinite.
     *
     * @return {@code true}, as the Infinite mode is infinite by design
     */
    @Override
    public boolean isInfinite() {
        return true;
    }
}


