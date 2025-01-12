package projectileFactory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import entity.Entity;
import entity.projectile.Projectile2;
import entity.projectile.Projectile3;
import mvc.Controller;
import mvc.View;
import mvc.projectileController.Projectile2Controller;
import mvc.projectileController.Projectile3Controller;
import random.EntityManager;

/**
 * Fabrique concrète pour créer des projectiles de type 3.
 * Cette classe implémente l'interface `ProjectileFactory` et définit la logique de création des entités,
 * des vues et des contrôleurs pour les projectiles de type 3.
 */
public class Projectile3Factory implements ProjectileFactory {

    /**
     * Crée une entité projectile de type 3 à une position donnée avec des propriétés spécifiques.
     * Cette méthode crée un objet `Projectile1` qui représente un projectile de type 3 dans le jeu.
     *
     * @param position La position initiale du projectile sur l'écran.
     * @param vect La direction et la vitesse du projectile.
     * @param health La santé du projectile.
     * @param isAlive Un booléen indiquant si le projectile est vivant ou non.
     * @param batch Le `SpriteBatch` utilisé pour le rendu du projectile.
     * @return L'entité `Projectile1` représentant le projectile de type 3.
     */
    @Override
    public Entity createEntity(Vector2 position, Vector2 vect, int health, boolean isAlive, SpriteBatch batch) {
        return new Projectile3(position, vect, batch);
    }

    /**
     * Crée une vue pour un projectile de type 3.
     * Cette méthode permet de créer la vue associée à l'entité projectile de type 3,
     * qui est responsable de son affichage visuel à l'écran.
     *
     * @param projectile L'entité projectile pour laquelle la vue doit être créée.
     * @return Un objet `View` représentant la vue du projectile de type 3.
     */
    @Override
    public View createView(Entity projectile) {
        return new View(projectile);
    }

    /**
     * Crée un contrôleur pour gérer le comportement d'un projectile de type 3.
     * Cette méthode détermine la logique de contrôle spécifique pour les projectiles de type 3,
     * y compris leur mouvement et leur interaction avec d'autres entités du jeu.
     *
     * @param projectile L'entité projectile pour laquelle un contrôleur doit être créé.
     * @param entityManager Le gestionnaire des entités utilisé pour interagir avec les autres entités du jeu.
     * @return Un objet `Controller` permettant de contrôler le comportement du projectile de type 3.
     */
    @Override
    public Controller createController(Entity projectile, EntityManager entityManager) {
        return new Projectile3Controller(projectile, entityManager);
    }
}
