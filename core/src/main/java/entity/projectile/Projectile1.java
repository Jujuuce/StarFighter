package entity.projectile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import entity.Projectile;

/**
 * Représente un projectile de type 1 (Projectile1) dans le jeu, dérivé de la classe `Projectile`.
 * Ce projectile utilise une texture spécifique pour son apparence et est configuré avec une santé,
 * une direction de déplacement et une texture de tir particulière.
 */
public class Projectile1 extends Projectile {

    /**
     * Constructeur d'un Projectile1.
     * Ce projectile est initialisé avec une position, une direction de déplacement (vecteur),
     * un SpriteBatch pour le rendu, et une texture spécifique pour le projectile.
     *
     * @param position la position initiale du projectile
     * @param vect le vecteur de direction du projectile
     * @param batch le SpriteBatch utilisé pour dessiner le projectile
     */
    public Projectile1(Vector2 position, Vector2 vect, SpriteBatch batch) {
        super(position, vect, 1, batch, "Skins/laserBlue08.png");
    }
}
