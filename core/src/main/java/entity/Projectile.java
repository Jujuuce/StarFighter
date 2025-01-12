package entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import hitbox.Rectangle;
import hitbox.RectangleCircleCollision;
import hitbox.RectangleRectangleCollision;
import random.AudioManager;

/**
 * Représente un projectile dans le jeu, dérivé de la classe `Entity`.
 * Un projectile a une direction (vecteur), une santé, et peut être un projectile allié ou ennemi.
 * Il se déplace sur l'écran et peut être détruit s'il sort des limites de l'écran ou atteint une certaine condition.
 */
public class Projectile extends Entity {
    private Vector2 vect;
    private boolean isAlly = false;

    /**
     * Constructeur d'un projectile.
     *
     * @param position la position initiale du projectile
     * @param vect le vecteur indiquant la direction et la vitesse du projectile
     * @param health la santé du projectile (souvent 1 pour les projectiles)
     * @param batch le SpriteBatch utilisé pour dessiner le projectile
     * @param pathTexture le chemin de la texture à utiliser pour afficher le projectile
     */
    public Projectile(Vector2 position, Vector2 vect, int health, SpriteBatch batch, String pathTexture) {
        super(position.x, position.y, health, batch, pathTexture);
        this.vect = vect;
        setX(getX() - getTexture().getWidth() / 2);
    }

    /**
     * Retourne une représentation sous forme de chaîne du projectile.
     *
     * @return une chaîne représentant le projectile
     */
    @Override
    public String toString() {
        return "projectile";
    }

    /**
     * Modifie la position du projectile en fonction de son vecteur de déplacement.
     * Si le projectile sort des limites de l'écran, il est détruit.
     *
     * @param velocity la vélocité à ajouter à la position du projectile (non utilisée ici, le vecteur est utilisé directement)
     */
    @Override
    public void changePos(Vector2 velocity) {
        super.changePos(vect);

        // Si le projectile sort des limites de l'écran, il est détruit
        if (getX() >= 1000 - getWidth() || getY() <= 0 || getY() >= 800 || getX() <= 0) {
            death();
        }
    }

    // Setter

    /**
     * Modifie le vecteur de déplacement du projectile.
     *
     * @param vect le nouveau vecteur de déplacement
     */
    @Override
    public void setVect(Vector2 vect) {
        this.vect = vect;
    }

    /**
     * Modifie le statut du projectile pour indiquer s'il est allié ou ennemi.
     *
     * @param ally {@code true} si le projectile est allié, {@code false} sinon
     */
    public void setAlly(boolean ally) {
        this.isAlly = ally;
    }

    // Getter

    /**
     * Retourne le vecteur de déplacement du projectile.
     *
     * @return le vecteur de déplacement
     */
    @Override
    public Vector2 getVect() {
        return vect;
    }

    /**
     * Retourne si le projectile est allié.
     *
     * @return {@code true} si le projectile est allié, {@code false} sinon
     */
    public boolean isAlly() {
        return isAlly;
    }
}
