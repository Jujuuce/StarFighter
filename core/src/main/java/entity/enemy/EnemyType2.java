package entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import entity.*;
import hitbox.Rectangle;
import hitbox.RectangleCircleCollision;
import hitbox.RectangleRectangleCollision;

/**
 * Représente un ennemi de type 2 (EnemyType2) dans le jeu, dérivé de la classe `Enemy`.
 * Cet ennemi utilise une texture spécifique pour son apparence et hérite des comportements généraux d'un ennemi.
 */
public class EnemyType2 extends Enemy {

    /**
     * Constructeur d'un EnemyType2.
     * Cet ennemi est initialisé avec une position, une santé, un SpriteBatch et une texture spécifique.
     *
     * @param X la position initiale en X de l'ennemi
     * @param Y la position initiale en Y de l'ennemi
     * @param health la santé de l'ennemi
     * @param batch le SpriteBatch utilisé pour dessiner l'ennemi
     */
    public EnemyType2(float X, float Y, int health, SpriteBatch batch) {
        super(X, Y, health, batch, "Skins/enemyGreen5.png");
    }
}
