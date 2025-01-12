package entity.projectile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import entity.Projectile;

/**
 * Représente un projectile de type 2 (Projectile2) dans le jeu, dérivé de la classe `Projectile`.
 * Ce projectile utilise une texture spécifique pour son apparence et est configuré avec une santé,
 * une direction de déplacement et une texture de tir particulière.
 */
public class Projectile2 extends Projectile {

    /**
     * Constructeur d'un Projectile2.
     * Ce projectile est initialisé avec une position, une direction de déplacement (vecteur),
     * un SpriteBatch pour le rendu, et une texture spécifique pour le projectile.
     *
     * @param position la position initiale du projectile
     * @param vect le vecteur de direction du projectile
     * @param batch le SpriteBatch utilisé pour dessiner le projectile
     */
    public Projectile2(Vector2 position, Vector2 vect, SpriteBatch batch) {
        super(position, vect, 1, batch, "Skins/laserGreen02.png");
    }
}
