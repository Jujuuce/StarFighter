package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import hitbox.Rectangle;
import hitbox.RectangleCircleCollision;
import hitbox.RectangleRectangleCollision;

/**
 * Représente un ennemi dans le jeu, dérivé de la classe `Entity`.
 * L'ennemi possède une position, une santé, une texture et une vitesse aléatoire qui lui permet de se déplacer de manière imprévisible.
 */
public class Enemy extends Entity {
    private Vector2 velocity = new Vector2(-1f + (float) Math.random() * 2f, -1f + (float) Math.random() * 2f);

    /**
     * Constructeur d'un ennemi.
     *
     * @param X la position initiale en X de l'ennemi
     * @param Y la position initiale en Y de l'ennemi
     * @param health la santé de l'ennemi
     * @param batch le SpriteBatch utilisé pour dessiner l'ennemi
     * @param pathTexture le chemin de la texture à utiliser pour afficher l'ennemi
     */
    public Enemy(float X, float Y, int health, SpriteBatch batch, String pathTexture) {
        super(X, Y, health, batch, pathTexture);
    }

    /**
     * Retourne une représentation sous forme de chaîne de l'ennemi.
     *
     * @return une chaîne représentant l'ennemi
     */
    @Override
    public String toString() {
        return "Enemy";
    }

    /**
     * Déplace l'ennemi en fonction de sa vitesse aléatoire et le contraint à l'intérieur de la fenêtre du jeu.
     * Si l'ennemi atteint les bords de la fenêtre, il rebondit en modifiant sa vitesse.
     */
    public void randomPos() {
        add(velocity);

        // Contrôle des limites horizontales et verticales
        if (getX() >= 1000 - getWidth()) {
            setX(1000 - getWidth());
            velocity = new Vector2(-1f + (float) Math.random() * 2f, -1f + (float) Math.random() * 2f);
        } else if (getX() <= 0) {
            setX(0);
            velocity = new Vector2(-1f + (float) Math.random() * 2f, -1f + (float) Math.random() * 2f);
        }
        if (getY() >= 800 - getHeight()) {
            setY(800 - getHeight());
            velocity = new Vector2(-1f + (float) Math.random() * 2f, -1f + (float) Math.random() * 2f);
        } else if (getY() <= 600) {
            setY(600);
            velocity = new Vector2(-1f + (float) Math.random() * 2f, -1f + (float) Math.random() * 2f);
        }

        // Mise à jour du point de tir de l'ennemi
        setFiringPoint(new Vector2(getX() + getWidth() / 2, getY()));
    }
}

