package projectileStrategy;

import com.badlogic.gdx.math.Vector2;
import entity.Entity;
import mvc.Controller;
import mvc.View;
import projectileFactory.Projectile1Factory;
import projectileFactory.Projectile2Factory;
import random.EntityManager;

import java.util.List;

/**
 * Stratégie de tir pour le projectile de type 2.
 * Cette classe implémente la méthode `fire` pour créer et gérer un projectile de type 2 tiré par une entité donnée.
 * Le projectile est créé avec une direction et une vitesse définies, puis ajouté à l'entité appropriée dans le gestionnaire d'entités.
 */
public class Projectile2Strategy implements ProjectileStrategy {

    /**
     * Implémente la logique de tir d'un projectile de type 2 par une entité donnée.
     * La méthode crée un projectile, lui assigne une direction et une vitesse, puis l'ajoute au gestionnaire d'entités.
     *
     * @param shooter L'entité qui tire le projectile (par exemple un joueur ou un ennemi).
     * @param targetPoint Le point cible ou la direction vers laquelle le projectile est tiré.
     * @param entityManager Le gestionnaire des entités, utilisé pour ajouter le projectile au bon groupe (ennemis, alliés, etc.).
     * @return Un contrôleur qui gère le projectile, permettant un contrôle supplémentaire (par exemple la gestion du mouvement ou de la collision).
     * @throws IllegalArgumentException Si l'un des arguments est invalide (null).
     * @throws RuntimeException Si une erreur survient lors de la création ou de l'ajout du projectile.
     */
    @Override
    public Controller fire(Entity shooter, Vector2 targetPoint, EntityManager entityManager) {
        // Validation des arguments
        if (shooter == null) {
            throw new IllegalArgumentException("Shooter cannot be null.");
        }
        if (targetPoint == null) {
            throw new IllegalArgumentException("Target point cannot be null.");
        }
        if (entityManager.getAllies() == null || entityManager.getEnemies() == null || entityManager.getControllers() == null) {
            throw new IllegalArgumentException("Allies, enemies, and controllers lists cannot be null.");
        }

        try {
            // Calcul directionnel
            Vector2 direction = new Vector2(0,-1);
            float speed = 20f;
            Vector2 velocity = direction.scl(speed);

            // Créer le projectile
            Entity projectile = new Projectile2Factory().createEntity(
                shooter.getFiringPoint(),
                velocity,
                10,
                true,
                shooter.getBatch()
            );

            View view = new View(projectile);
            Controller controller = new Projectile2Factory().createController(projectile, entityManager);

            // Ajouter le projectile aux ennemis
            entityManager.addEnemy(projectile);

            return controller;

        } catch (Exception e) {
            throw new RuntimeException("Error while firing projectile: " + e.getMessage(), e);
        }
    }
}
