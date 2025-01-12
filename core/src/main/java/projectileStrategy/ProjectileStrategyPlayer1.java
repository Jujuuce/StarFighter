package projectileStrategy;

import com.badlogic.gdx.math.Vector2;
import entity.Entity;
import entity.Projectile;
import mvc.Controller;
import mvc.View;
import projectileFactory.Projectile2Factory;
import projectileFactory.Projectile3Factory;
import random.EntityManager;

import java.util.List;

/**
 * Stratégie de tir pour le projectile du joueur 1.
 * Cette classe implémente la méthode `fire` pour créer et gérer un projectile allié tiré par le joueur.
 * Le projectile est créé pour se déplacer verticalement vers le haut, avec une vitesse déterminée.
 * Une fois créé, il est ajouté à la liste des alliés du gestionnaire d'entités.
 */
public class ProjectileStrategyPlayer1 implements ProjectileStrategy {

    /**
     * Implémente la logique de tir d'un projectile allié par le joueur 1.
     * Cette méthode crée un projectile, lui assigne une direction vers le haut (verticale) et une vitesse,
     * puis l'ajoute au gestionnaire d'entités comme un projectile allié.
     *
     * @param shooter L'entité qui tire le projectile (en l'occurrence, le joueur 1).
     * @param v Le point cible ou une direction vers laquelle le projectile est tiré. Non utilisé dans cette stratégie.
     * @param entityManager Le gestionnaire des entités, utilisé pour ajouter le projectile au groupe des alliés.
     * @return Un contrôleur qui gère le projectile, permettant un contrôle supplémentaire (par exemple la gestion du mouvement ou de la collision).
     * @throws IllegalArgumentException Si l'un des arguments est invalide (null).
     * @throws IllegalStateException Si l'entité créée n'est pas un projectile.
     * @throws RuntimeException Si une erreur survient lors de la création ou de l'ajout du projectile.
     */
    @Override
    public Controller fire(Entity shooter, Vector2 v, EntityManager entityManager) {
        // Validation des arguments
        if (shooter == null) {
            throw new IllegalArgumentException("Shooter cannot be null.");
        }
        if (entityManager.getAllies() == null || entityManager.getEnemies() == null || entityManager.getControllers() == null) {
            throw new IllegalArgumentException("Allies, enemies, and controllers lists cannot be null.");
        }

        try {
            // Calcul directionnel
            Vector2 direction = new Vector2(0, 1); // Projectile monte verticalement
            float speed = 15f;
            Vector2 velocity = direction.scl(speed);

            // Créer le projectile standard
            Entity projectile = new Projectile3Factory().createEntity(
                shooter.getFiringPoint(),
                velocity,
                10,
                true,
                shooter.getBatch()
            );

            if (!(projectile instanceof Projectile)) {
                throw new IllegalStateException("Created entity is not a projectile.");
            }

            // Configurer le projectile
            ((Projectile) projectile).setAlly(true);

            // Créer la vue et le contrôleur
            View view = new View(projectile);
            Controller controller = new Projectile3Factory().createController(projectile, entityManager);

            // Ajouter le projectile à la liste des alliés
            entityManager.addAlly(projectile);

            return controller;

        } catch (Exception e) {
            throw new RuntimeException("Error while firing ally projectile: " + e.getMessage(), e);
        }
    }
}

