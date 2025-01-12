package projectileStrategy;

import com.badlogic.gdx.math.Vector2;
import entity.Entity;
import mvc.Controller;
import random.EntityManager;

import java.util.List;


/**
 * Interface qui définit la stratégie pour le tir d'un projectile.
 * Elle est utilisée pour permettre à différentes stratégies de tir d'être implémentées pour des entités, comme un joueur ou un ennemi.
 */
public interface ProjectileStrategy {

    /**
     * Méthode qui définit comment un projectile est tiré par une entité donnée.
     * Chaque stratégie peut définir son propre comportement de tir, par exemple, la direction du tir, la vitesse, etc.
     *
     * @param shooter L'entité qui tire le projectile (ex. : un joueur ou un ennemi).
     * @param targetPoint Le point de destination ou la direction du tir.
     * @param entityManager Le gestionnaire des entités, utilisé pour ajouter le projectile à la scène.
     * @return Un contrôleur qui gère le tir du projectile, permettant éventuellement des actions supplémentaires sur le tir.
     */
    Controller fire(Entity shooter, Vector2 targetPoint, EntityManager entityManager);
}
