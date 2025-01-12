package mvc.projectileController;

import entity.Entity;
import mvc.Controller;
import random.EntityManager;

import java.util.List;

/**
 * La classe `Projectile2Controller` est un contrôleur spécifique pour gérer le comportement d'un projectile de type 2.
 * Elle hérite de la classe `Controller` et permet de gérer l'affichage et la mise à jour de la position du projectile.
 */
public class Projectile2Controller extends Controller {

    /**
     * Constructeur de la classe `Projectile2Controller`.
     * Initialise le contrôleur avec l'entité et le gestionnaire d'entités.
     *
     * @param entity L'entité associée à ce contrôleur.
     * @param entityManager Le gestionnaire d'entités pour gérer cette entité dans le jeu.
     */
    public Projectile2Controller(Entity entity, EntityManager entityManager) {
        super(entity, entityManager);
    }

    /**
     * Met à jour le contrôleur du projectile.
     * Déplace le projectile en fonction de sa vitesse et marque son apparition.
     */
    @Override
    public void update() {
        changePos(getVect());  // Met à jour la position du projectile selon sa vélocité.
        super.update();         // Appelle la mise à jour de la classe parente.
        setAppear(true);        // Marque le projectile comme apparaissant.
    }

    /**
     * Met à jour le contrôleur du projectile avec une liste de contrôleurs.
     * Déplace le projectile en fonction de sa vitesse et marque son apparition.
     *
     * @param controllers La liste des contrôleurs actifs (utilisé pour la gestion des interactions avec d'autres objets).
     */
    @Override
    public void update(List<Controller> controllers) {
        changePos(getVect());  // Met à jour la position du projectile selon sa vélocité.
        super.update();         // Appelle la mise à jour de la classe parente.
        setAppear(true);        // Marque le projectile comme apparaissant.
    }
}

