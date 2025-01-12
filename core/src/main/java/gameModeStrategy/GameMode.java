package gameModeStrategy;

import entity.Entity;
import game.StarFighter;
import mvc.MapModel;
import random.EntityManager;

/**
 * Interface représentant un mode de jeu dans le jeu StarFighter.
 * <p>
 * Un mode de jeu définit le comportement des ennemis, la gestion des collisions, ainsi que les règles spécifiques à ce mode.
 * </p>
 */
public interface GameMode {

    /**
     * Gère l'apparition des entités (comme les ennemis) dans le jeu, en fonction du mode de jeu.
     *
     * @param lists le gestionnaire des entités du jeu, qui contient les ennemis, les contrôleurs, etc.
     * @param mapModel le modèle de la carte utilisée dans le jeu, qui peut contenir des informations spécifiques à la carte
     * @param game l'instance du jeu en cours d'exécution
     */
    void handleSpawning(EntityManager lists, MapModel mapModel, StarFighter game);

    /**
     * Indique si le mode de jeu est infini, c'est-à-dire si le jeu continue indéfiniment sans fin définie.
     *
     * @return {@code true} si le mode de jeu est infini, {@code false} sinon
     */
    boolean isInfinite();
}
