package gameModeStrategy;

import entity.Entity;
import game.StarFighter;
import mvc.MapModel;
import random.EntityManager;

/**
 * Represents the Classic game mode for the StarFighter game.
 *
 * <p>In this mode, enemies are managed via the map's configuration,
 * and the game ends when the player's entity is no longer alive or the boss is defeated.</p>
 */
public class ClassicMode implements GameMode {

    /**
     * Handles the spawning of entities for the Classic game mode.
     *
     * <p>In the Classic mode, enemies are spawned based on the map's configuration
     * using the {@code loadEnemiesFromMap()} method.</p>
     *
     * @param lists    the {@link EntityManager} managing the entities in the game
     * @param mapModel the {@link MapModel} containing the map's data
     * @param game     the {@link StarFighter} game instance
     */
    @Override
    public void handleSpawning(EntityManager lists, MapModel mapModel, StarFighter game) {
        // Enemies are managed via the map's configuration in loadEnemiesFromMap()
    }


    /**
     * Indicates whether this game mode is infinite.
     *
     * @return {@code false}, as the Classic mode is not infinite
     */
    @Override
    public boolean isInfinite() {
        return false;
    }
}

