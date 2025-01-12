package random;

import entity.Entity;
import mvc.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe responsable de la gestion des entités du jeu, y compris les alliés, les ennemis et les contrôleurs.
 * Elle permet de mettre à jour les entités, de les ajouter aux listes appropriées et de gérer leur cycle de vie.
 */
public class EntityManager {
    private List<Entity> allies;
    private List<Entity> enemies;
    private List<Controller> controllers;

    /**
     * Constructeur de la classe `EntityManager`.
     * Initialise les listes d'alliés, d'ennemis et de contrôleurs.
     */
    public EntityManager() {
        allies = new ArrayList<>();
        enemies = new ArrayList<>();
        controllers = new ArrayList<>();
    }

    /**
     * Met à jour toutes les entités (alliés et ennemis).
     * Supprime les entités qui ne sont plus vivantes.
     */
    public void updateEntities() {
        // Parcours des alliés
        Iterator<Entity> iterator = allies.iterator();
        while (iterator.hasNext()) {
            Entity ally = iterator.next();
            ally.update();
            if (!ally.isAlive()) {
                iterator.remove(); // Utilise iterator.remove() pour supprimer en toute sécurité
            }
        }

        // Parcours des ennemis
        Iterator<Entity> iteratorE = enemies.iterator();
        while (iteratorE.hasNext()) {
            Entity enemy = iteratorE.next();
            enemy.update();
            if (!enemy.isAlive()) {
                iteratorE.remove(); // Utilise iteratorE.remove() pour supprimer en toute sécurité
            }
        }
    }

    /**
     * Met à jour tous les contrôleurs.
     * Supprime les contrôleurs qui ne sont plus vivants et ajoute de nouveaux contrôleurs à la liste.
     */
    public void updateControllers() {
        // Mise à jour des contrôleurs
        List<Controller> newControllers = new ArrayList<>();
        Iterator<Controller> iterator = controllers.iterator();

        while (iterator.hasNext()) {
            Controller controller = iterator.next();

            controller.update(newControllers);

            if (!controller.isAlive()) {
                iterator.remove();
            }
        }

        controllers.addAll(newControllers);
    }

    // Setters

    /**
     * Ajoute un allié à la liste des alliés.
     *
     * @param entity L'entité alliée à ajouter.
     * @throws IllegalArgumentException Si l'entité est nulle.
     */
    public void addAlly(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Ally entity cannot be null");
        }
        allies.add(entity);
    }

    /**
     * Ajoute un ennemi à la liste des ennemis.
     *
     * @param entity L'entité ennemie à ajouter.
     * @throws IllegalArgumentException Si l'entité est nulle.
     */
    public void addEnemy(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Enemy entity cannot be null");
        }
        enemies.add(entity);
    }

    /**
     * Ajoute un contrôleur à la liste des contrôleurs.
     *
     * @param controller Le contrôleur à ajouter.
     * @throws IllegalArgumentException Si le contrôleur est nul.
     */
    public void addController(Controller controller) {
        if (controller == null) {
            throw new IllegalArgumentException("Controller cannot be null");
        }
        controllers.add(controller);
    }

    // Getters

    /**
     * Retourne la liste des alliés.
     *
     * @return La liste des alliés.
     */
    public List<Entity> getAllies() {
        return allies;
    }

    /**
     * Retourne la liste des ennemis.
     *
     * @return La liste des ennemis.
     */
    public List<Entity> getEnemies() {
        return enemies;
    }

    /**
     * Retourne la liste des contrôleurs.
     *
     * @return La liste des contrôleurs.
     */
    public List<Controller> getControllers() {
        return controllers;
    }

    /**
     * Vérifie si la liste des ennemis est vide.
     *
     * @return true si la liste des ennemis est vide, false sinon.
     */
    public boolean isEmptyEnemies() {
        return enemies.isEmpty();
    }
}


