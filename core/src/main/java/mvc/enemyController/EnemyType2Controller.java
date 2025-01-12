package mvc.enemyController;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import entity.*;
import mvc.*;
import projectileStrategy.Projectile1Strategy;
import projectileStrategy.Projectile2Strategy;
import random.EntityManager;

import java.util.List;

/**
 * Le contrôleur `EnemyType2Controller` gère le comportement d'un ennemi de type 2 dans le jeu.
 * Il hérite de la classe `Controller` et définit la logique de déplacement et d'attaque de l'ennemi.
 */
public class EnemyType2Controller extends Controller {

    /**
     * Constructeur de la classe `EnemyType1Controller`.
     * Initialise le contrôleur avec l'entité de l'ennemi et le gestionnaire d'entités.
     * Définit la vélocité de l'ennemi et la stratégie de projectile pour ses attaques.
     *
     * @param entity L'entité associée au contrôleur (l'ennemi).
     * @param entityManager Le gestionnaire d'entités qui gère toutes les entités du jeu.
     */
    public EnemyType2Controller(Entity entity, EntityManager entityManager) {
        super(entity, entityManager);
        setVelocity(new Vector2(0, -1));  // Définit la vitesse de l'ennemi.
        setProjectileStrategy(new Projectile2Strategy());  // Définit la stratégie de projectile pour l'ennemi.
    }

    /**
     * Met à jour le comportement de l'ennemi en fonction de son état actuel.
     * Si l'ennemi n'est pas encore apparu, il se déplace. Sinon, il effectue des actions supplémentaires comme
     * changer de position aléatoirement et attaquer le joueur après un certain délai.
     *
     * @param newControllers La liste des nouveaux contrôleurs à ajouter (gère la création de nouveaux projectiles).
     */
    @Override
    public void update(List<Controller> newControllers) {
        if (!isAppear()) {
            // L'ennemi n'est pas encore apparu, il se déplace selon sa vélocité.
            changePos(getVelocity());
        } else {
            // Une fois que l'ennemi est apparu, il change de position aléatoirement et attaque le joueur.
            ((Enemy)getEntity()).randomPos();  // L'ennemi se déplace aléatoirement.
            increaseDelay();  // Augmente le délai avant l'attaque suivante.

            // Lorsque le délai dépasse 50, l'ennemi tire un projectile vers le joueur.
            if (getDelay() > 50) {
                newControllers.add(fireProjectile(getPlayer().getPosition()));  // Tire un projectile vers la position du joueur.
                resetDelay();  // Réinitialise le délai avant la prochaine attaque.
            }
        }
        super.update();  // Appelle la mise à jour de la classe parente.
    }
}

