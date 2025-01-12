package mvc;

import com.badlogic.gdx.math.Vector2;
import entity.*;
import projectileFactory.Projectile1Factory;
import projectileFactory.ProjectileFactory;
import projectileStrategy.ProjectileStrategy;
import random.AudioManager;
import random.EntityManager;

import java.lang.ModuleLayer;
import java.util.List;

/**
 * Classe responsable de la gestion des contrôles et de l'interaction entre un entité et le jeu.
 * Elle permet de manipuler l'entité associée, de gérer les projectiles et les comportements du contrôleur,
 * comme la vitesse, l'apparition et la destruction de l'entité.
 */
public class Controller {
    private Entity entity;
    private boolean appear = false;
    private Vector2 velocity;
    private EntityManager entityManager;
    private ProjectileFactory projectileFactory;
    private int delay = 0;
    private ProjectileStrategy projectileStrategy;
    private AudioManager audioManager;

    /**
     * Constructeur de la classe `Controller`.
     *
     * @param entity L'entité à laquelle ce contrôleur est associé.
     * @param entityManager Le gestionnaire d'entités qui gère toutes les entités du jeu.
     */
    public Controller(Entity entity, EntityManager entityManager) {
        this.entity = entity;
        this.entityManager = entityManager;
        setProjectileFactory(new Projectile1Factory());
        audioManager = AudioManager.getInstance();
    }

    /**
     * Met à jour l'état du contrôleur et de l'entité associée.
     * Cette méthode contrôle l'apparition de l'entité selon sa position sur l'axe Y.
     */
    public void update() {
        if (entity.getY() < 700) {
            appear = true;
            velocity = new Vector2(0, 0);
        }
    }

    /**
     * Méthode de mise à jour avec une liste de contrôleurs, utilisée pour des logiques complexes de mise à jour.
     * Cette méthode peut être étendue par les classes dérivées.
     *
     * @param controllers La liste de contrôleurs à mettre à jour.
     */
    public void update(List<Controller> controllers) {}

    /**
     * Détruit l'entité associée et effectue la gestion de sa mort.
     */
    public void destroy() {
        entity.death();
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du contrôleur.
     *
     * @return Une chaîne représentant le contrôleur.
     */
    @Override
    public String toString() {
        return "controller";
    }

    /**
     * Définit la fabrique de projectiles utilisée par ce contrôleur.
     *
     * @param factory La fabrique de projectiles.
     */
    public void setProjectileFactory(ProjectileFactory factory) {
        this.projectileFactory = factory;
    }

    /**
     * Tire un projectile en utilisant la stratégie de projectile définie et en ciblant un point.
     *
     * @param targetPoint Le point cible pour le tir du projectile.
     * @return Un contrôleur pour gérer le tir du projectile.
     * @throws IllegalStateException Si aucune stratégie de projectile n'est définie.
     */
    public Controller fireProjectile(Vector2 targetPoint) {
        if (projectileStrategy == null) {
            throw new IllegalStateException("Projectile strategy is not set.");
        }
        audioManager.playSound("fire");
        return projectileStrategy.fire(entity, targetPoint, entityManager);
    }

    /**
     * Change la position de l'entité en fonction du vecteur de vitesse.
     *
     * @param velocity Le vecteur de vitesse pour déplacer l'entité.
     */
    public void changePos(Vector2 velocity) {
        entity.changePos(velocity);
    }

    /**
     * Définit si l'entité est apparue ou non dans le jeu.
     *
     * @param appear true si l'entité doit apparaître, sinon false.
     */
    public void setAppear(boolean appear) {
        this.appear = appear;
    }

    /**
     * Définit la vitesse de l'entité.
     *
     * @param vect Le vecteur de vitesse.
     * @throws IllegalArgumentException Si le vecteur de vitesse est nul.
     */
    public void setVelocity(Vector2 vect) {
        if (vect == null) {
            throw new IllegalArgumentException("Velocity vector cannot be null.");
        }
        this.velocity = vect;
    }

    /**
     * Réinitialise le délai de ce contrôleur à zéro.
     */
    public void resetDelay() {
        this.delay = 0;
    }

    /**
     * Augmente le délai de ce contrôleur.
     */
    public void increaseDelay() {
        this.delay++;
    }

    /**
     * Définit la stratégie de projectile utilisée par ce contrôleur.
     *
     * @param projectileStrategy La stratégie de projectile.
     */
    public void setProjectileStrategy(ProjectileStrategy projectileStrategy) {
        this.projectileStrategy = projectileStrategy;
    }

    // Getters

    /**
     * Retourne le vecteur de la position de l'entité associée.
     *
     * @return Le vecteur de position de l'entité.
     */
    public Vector2 getVect() {
        return entity.getVect();
    }

    /**
     * Vérifie si l'entité associée est toujours vivante.
     *
     * @return true si l'entité est vivante, sinon false.
     */
    public boolean isAlive() {
        return entity.isAlive();
    }

    /**
     * Vérifie si l'entité doit apparaître dans le jeu.
     *
     * @return true si l'entité doit apparaître, sinon false.
     */
    public boolean isAppear() {
        return appear;
    }

    /**
     * Retourne le vecteur de vitesse de l'entité.
     *
     * @return Le vecteur de vitesse.
     */
    public Vector2 getVelocity() {
        return velocity;
    }

    /**
     * Retourne le délai actuel de ce contrôleur.
     *
     * @return Le délai.
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Retourne l'entité associée à ce contrôleur.
     *
     * @return L'entité associée.
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Retourne le premier allié du gestionnaire d'entités (généralement le joueur).
     *
     * @return L'entité alliée (le joueur).
     */
    public Entity getPlayer() {
        return entityManager.getAllies().get(0);
    }
}

