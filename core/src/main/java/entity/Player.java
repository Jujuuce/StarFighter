package entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import hitbox.Rectangle;
import hitbox.RectangleCircleCollision;
import hitbox.RectangleRectangleCollision;
import projectileFactory.ProjectileFactory;
import ui.ScoresManager;

/**
 * Représente un joueur dans le jeu, dérivé de la classe `Entity`.
 * Un joueur possède un score, une santé, et peut devenir invincible pendant un certain temps après avoir subi des dégâts.
 * La classe gère la position du joueur, les déplacements, la gestion des collisions et l'invincibilité.
 */
public class Player extends Entity {
    private int score;
    private boolean isInvincible = false;
    private float invincibilityTimer = 0;
    private float invincibilityDuration = 1.5f;

    /**
     * Constructeur d'un joueur.
     *
     * @param X la position X du joueur
     * @param Y la position Y du joueur
     * @param health les points de vie du joueur
     * @param batch le SpriteBatch utilisé pour dessiner le joueur
     */
    public Player(float X, float Y, int health, SpriteBatch batch) {
        super(X, Y, health, batch, "Skins/playerShip3_red.png");
        score = 0;
        setFiringPoint(new Vector2(getX() + getWidth() / 2, getY() + getHeight()));
    }

    /**
     * Retourne une représentation sous forme de chaîne du joueur.
     *
     * @return une chaîne représentant le joueur
     */
    @Override
    public String toString() {
        return "player";
    }

    /**
     * Modifie la position du joueur en fonction d'une vélocité donnée.
     * Empêche le joueur de sortir des limites de l'écran.
     *
     * @param velocity la vélocité à ajouter à la position du joueur
     */
    @Override
    public void changePos(Vector2 velocity) {
        add(velocity);

        // Empêche le joueur de dépasser les bords de l'écran
        if (getX() >= 1000 - getWidth()) {
            setX(1000 - getWidth());
        } else if (getX() <= 0) {
            setX(0);
        }
        if (getY() >= 800 - getHeight()) {
            setY(800 - getHeight());
        } else if (getY() <= 50) {
            setY(50);
        }

        // Met à jour le point de tir du joueur
        setFiringPoint(new Vector2(getX() + getWidth() / 2, getY() + getHeight()));
    }

    /**
     * Applique des dégâts au joueur. Si le joueur est invincible, les dégâts sont ignorés.
     * Si le joueur n'est pas invincible, il subit les dégâts et devient invincible pendant un certain temps.
     *
     * @param damage les points de dégâts à infliger
     */
    @Override
    public void takeDamage(int damage) {
        if (isInvincible) {
            // Ignore les dégâts si le joueur est invincible
            return;
        }

        super.takeDamage(damage);
        // Active l'invincibilité après avoir pris des dégâts
        if (isAlive()) {
            isInvincible = true;
            invincibilityTimer = invincibilityDuration; // Réinitialise le timer
        }
    }

    /**
     * Met à jour le statut d'invincibilité du joueur.
     * Réduit le timer d'invincibilité et désactive l'invincibilité lorsque le temps est écoulé.
     */
    public void updateInvincibility() {
        if (isInvincible) {
            invincibilityTimer -= Gdx.graphics.getDeltaTime(); // Réduit le timer basé sur le temps écoulé
            if (invincibilityTimer <= 0) {
                isInvincible = false; // L'invincibilité expire
            }
        }
    }

    // Getters et Setters

    /**
     * Retourne le score du joueur.
     *
     * @return le score du joueur
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Retourne si le joueur est actuellement invincible.
     *
     * @return {@code true} si le joueur est invincible, {@code false} sinon
     */
    public boolean isInvincible() {
        return isInvincible;
    }

    /**
     * Modifie le score du joueur.
     *
     * @param score le nouveau score du joueur
     */
    @Override
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Modifie le statut d'invincibilité du joueur.
     *
     * @param invincible {@code true} pour rendre le joueur invincible, {@code false} sinon
     */
    public void setInvincibility(boolean invincible) {
        isInvincible = invincible;
    }
}
