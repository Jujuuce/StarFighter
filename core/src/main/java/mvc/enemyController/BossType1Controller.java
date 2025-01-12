package mvc.enemyController;

import com.badlogic.gdx.math.Vector2;
import entity.Enemy;
import entity.Entity;
import mvc.Controller;
import mvc.View;
import projectileStrategy.Projectile1Strategy;
import random.EntityManager;

import java.util.List;

/**
 * Le contrôleur `BossType1Controller` est spécifique pour gérer le comportement du premier type de boss dans le jeu.
 * Il hérite de la classe `Controller` et permet de gérer la logique de déplacement et d'attaque du boss.
 */
public class BossType1Controller extends Controller {

    /**
     * Constructeur de la classe `BossType1Controller`.
     * Initialise le contrôleur avec l'entité du boss et le gestionnaire d'entités.
     * Définit la vélocité du boss et la stratégie de projectile pour ses attaques.
     *
     * @param entity L'entité associée au contrôleur (le boss).
     * @param entityManager Le gestionnaire d'entités qui gère toutes les entités du jeu.
     */
    public BossType1Controller(Entity entity, EntityManager entityManager) {
        super(entity, entityManager);
        setVelocity(new Vector2(0, -5));  // Définit la vitesse du boss.
        setProjectileStrategy(new Projectile1Strategy());  // Définit la stratégie de projectile pour le boss.
    }

    /**
     * Met à jour le comportement du boss en fonction de son état actuel.
     * Si le boss n'est pas encore apparu, il se déplace. Sinon, il effectue des actions supplémentaires comme
     * changer de position aléatoirement et attaquer le joueur après un certain délai.
     *
     * @param newControllers La liste des nouveaux contrôleurs à ajouter (gère la création de nouveaux projectiles).
     */
    @Override
    public void update(List<Controller> newControllers) {
        if (!isAppear()) {
            // Le boss n'est pas encore apparu, il se déplace selon sa vélocité.
            changePos(getVelocity());
        } else {
            // Une fois que le boss est apparu, il change de position aléatoirement et attaque le joueur.
            ((Enemy)getEntity()).randomPos();  // Le boss se déplace aléatoirement.
            increaseDelay();  // Augmente le délai avant l'attaque suivante.

            // Lorsque le délai dépasse 50, le boss tire un projectile vers le joueur.
            if (getDelay() > 50) {
                newControllers.add(fireProjectile(getPlayer().getPosition()));  // Tire un projectile vers la position du joueur.
                resetDelay();  // Réinitialise le délai avant la prochaine attaque.
            }
        }
        super.update();  // Appelle la mise à jour de la classe parente.
    }
}

