package entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import entity.Enemy;

/**
 * Représente un type de boss (BossType1) dans le jeu, dérivé de la classe `Enemy`.
 * Ce boss utilise une texture spécifique et possède les comportements d'un ennemi, mais avec des caractéristiques uniques.
 */
public class BossType1 extends Enemy {

    /**
     * Constructeur d'un BossType1.
     * Ce boss est initialisé avec une position, une santé, un SpriteBatch et une texture spécifique.
     *
     * @param X la position initiale en X du boss
     * @param Y la position initiale en Y du boss
     * @param health la santé du boss
     * @param batch le SpriteBatch utilisé pour dessiner le boss
     */
    public BossType1(float X, float Y, int health, SpriteBatch batch) {
        super(X, Y, health, batch, "Skins/ufoBlue.png");
    }
}
