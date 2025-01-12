package entity.projectile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import entity.Projectile;

public class Projectile3 extends Projectile {

    /**
     * Constructeur d'un Projectile3.
     * Ce projectile est initialisé avec une position, une direction de déplacement (vecteur),
     * un SpriteBatch pour le rendu, et une texture spécifique pour le projectile.
     *
     * @param position la position initiale du projectile
     * @param vect le vecteur de direction du projectile
     * @param batch le SpriteBatch utilisé pour dessiner le projectile
     */
    public Projectile3(Vector2 position, Vector2 vect, SpriteBatch batch) {
        super(position, vect, 1, batch, "Skins/laserRed13.png");
    }
}
