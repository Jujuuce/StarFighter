package projectileFactory;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entity.*;
import mvc.*;
import com.badlogic.gdx.math.Vector2;
import random.EntityManager;

import java.util.List;

/**
 * Interface représentant une fabrique pour la création de projectiles.
 * Cette interface définit les méthodes nécessaires pour créer un projectile, sa vue et son contrôleur.
 * Les implémentations spécifiques de cette interface détermineront la logique exacte pour la création et la gestion des projectiles.
 */
public interface ProjectileFactory {

    /**
     * Crée une entité projectile à une position donnée avec des propriétés spécifiques.
     * Cette méthode est responsable de la création du projectile lui-même, avec sa position, sa direction,
     * sa santé, son état de vie et son lot d'objets associés comme le `SpriteBatch` pour le rendu.
     *
     * @param position La position initiale du projectile sur l'écran.
     * @param vect La direction et la vitesse du projectile.
     * @param health La santé du projectile.
     * @param isAlive Un booléen indiquant si le projectile est vivant ou non.
     * @param batch Le `SpriteBatch` utilisé pour le rendu du projectile.
     * @return L'entité `Entity` représentant le projectile.
     */
    Entity createEntity(Vector2 position, Vector2 vect, int health, boolean isAlive, SpriteBatch batch);

    /**
     * Crée une vue pour un projectile, utilisée pour le rendre à l'écran.
     * Cette méthode permet de générer la vue associée au projectile, responsable de son affichage visuel.
     *
     * @param projectile L'entité projectile pour laquelle la vue doit être créée.
     * @return Un objet `View` représentant la vue du projectile.
     */
    View createView(Entity projectile);

    /**
     * Crée un contrôleur pour gérer le comportement d'un projectile.
     * Cette méthode détermine la logique de contrôle du projectile, comme son déplacement ou ses interactions avec d'autres entités.
     *
     * @param projectile L'entité projectile pour laquelle un contrôleur doit être créé.
     * @param entityManager Le gestionnaire des entités, utilisé pour interagir avec les autres entités du jeu.
     * @return Un objet `Controller` permettant de contrôler le comportement du projectile.
     */
    Controller createController(Entity projectile, EntityManager entityManager);
}
