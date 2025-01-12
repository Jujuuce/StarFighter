package projectileFactory;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entity.projectile.Projectile1;
import mvc.*;
import entity.*;
import com.badlogic.gdx.math.Vector2;
import mvc.projectileController.Projectile1Controller;
import random.EntityManager;

import java.util.List;

/**
 * Fabrique concrète pour créer des projectiles de type 1.
 * Cette classe implémente l'interface `ProjectileFactory` et définit la logique de création des entités,
 * des vues et des contrôleurs pour les projectiles de type 1.
 */
public class Projectile1Factory implements ProjectileFactory {

    /**
     * Crée une entité projectile de type 1 à une position donnée avec des propriétés spécifiques.
     * Cette méthode crée un objet `Projectile1` qui représente un projectile de type 1 dans le jeu.
     *
     * @param position La position initiale du projectile sur l'écran.
     * @param vect La direction et la vitesse du projectile.
     * @param health La santé du projectile.
     * @param isAlive Un booléen indiquant si le projectile est vivant ou non.
     * @param batch Le `SpriteBatch` utilisé pour le rendu du projectile.
     * @return L'entité `Projectile1` représentant le projectile de type 1.
     */
    @Override
    public Entity createEntity(Vector2 position, Vector2 vect, int health, boolean isAlive, SpriteBatch batch) {
        return new Projectile1(position, vect, batch);
    }

    /**
     * Crée une vue pour un projectile de type 1.
     * Cette méthode permet de créer la vue associée à l'entité projectile de type 1,
     * qui est responsable de son affichage visuel à l'écran.
     *
     * @param projectile L'entité projectile pour laquelle la vue doit être créée.
     * @return Un objet `View` représentant la vue du projectile de type 1.
     */
    @Override
    public View createView(Entity projectile) {
        return new View(projectile);
    }

    /**
     * Crée un contrôleur pour gérer le comportement d'un projectile de type 1.
     * Cette méthode détermine la logique de contrôle spécifique pour les projectiles de type 1,
     * y compris leur mouvement et leur interaction avec d'autres entités du jeu.
     *
     * @param projectile L'entité projectile pour laquelle un contrôleur doit être créé.
     * @param entityManager Le gestionnaire des entités utilisé pour interagir avec les autres entités du jeu.
     * @return Un objet `Controller` permettant de contrôler le comportement du projectile de type 1.
     */
    @Override
    public Controller createController(Entity projectile, EntityManager entityManager) {
        return new Projectile1Controller(projectile, entityManager);
    }
}

