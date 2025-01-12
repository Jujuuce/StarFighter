package entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import observer.*;
import hitbox.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import mvc.*;
import random.AudioManager;



/**
 * Représente une entité du jeu, telle qu'un personnage, un ennemi ou un projectile.
 * Une entité possède une position (hitbox), une texture pour son affichage, des points de vie,
 * et un point de tir pour les projectiles. Elle peut subir des dégâts, interagir avec d'autres entités
 * et être dessinée à l'écran.
 */
public class Entity extends Observable {
    private Shape hitbox;
    private int health;
    private Texture texture;
    private SpriteBatch batch;
    private Vector2 size;
    private boolean isAlive = true;
    private Vector2 firingPoint;

    /**
     * Constructeur d'une entité avec des paramètres de position, de points de vie,
     * de texture et de batch.
     *
     * @param X la position X de l'entité
     * @param Y la position Y de l'entité
     * @param health les points de vie de l'entité
     * @param batch le SpriteBatch utilisé pour dessiner l'entité
     * @param pathTexture le chemin du fichier de texture de l'entité
     */
    public Entity(float X, float Y, int health, SpriteBatch batch, String pathTexture) {
        setBatch(batch);
        setHealth(health);
        setTexture(new Texture(pathTexture));
        setHitbox(new Vector2(X, Y));
        setFiringPoint(new Vector2(getX() + getWidth() / 2, getY()));
    }

    /**
     * Constructeur par défaut d'une entité, avec des points de vie par défaut.
     */
    public Entity() {
        setHealth(10);
    }

    /**
     * Modifie la position de l'entité en fonction d'une vélocité donnée.
     *
     * @param velocity la vélocité à ajouter à la position de l'entité
     * @throws IllegalArgumentException si la vélocité est nulle
     */
    public void changePos(Vector2 velocity) {
        if (velocity == null) {
            throw new IllegalArgumentException("Velocity cannot be null.");
        }
        hitbox.add(velocity);
        if (firingPoint != null) {
            firingPoint.add(velocity);
        }
    }

    public void setHitbox(Vector2 position) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null.");
        }
        hitbox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        hitbox.setCollisionStrategy(new RectangleCircleCollision());
        hitbox.setCollisionStrategy(new RectangleRectangleCollision());
        size = new Vector2(texture.getWidth(), texture.getHeight());
    }

    /**
     * Applique des dégâts à l'entité. Si l'entité subit des dégâts, elle joue un son de "hit".
     * Si la santé de l'entité atteint zéro, elle meurt.
     *
     * @param damage les points de dégâts à infliger
     * @throws IllegalArgumentException si les dégâts sont négatifs
     */
    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative.");
        }
        if (!(this instanceof Projectile)) {
            AudioManager audio = AudioManager.getInstance();
            audio.playSound("hit");
        }
        health -= damage;
        if (health <= 0) {
            isAlive = false;
        }
    }

    /**
     * Commence le processus de rendu de l'entité en utilisant le SpriteBatch.
     */
    public void beginBatch() {
        if (batch == null) {
            throw new NullPointerException("SpriteBatch cannot be null.");
        }
        batch.begin();
    }

    /**
     * Termine le processus de rendu de l'entité avec le SpriteBatch.
     */
    public void endBatch() {
        if (batch == null) {
            throw new NullPointerException("SpriteBatch cannot be null.");
        }
        batch.end();
    }

    /**
     * Libère les ressources associées à l'entité, comme la texture.
     */
    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }

    /**
     * Marque l'entité comme morte.
     */
    public void death() {
        isAlive = false;
    }

    /**
     * Met à jour l'entité et notifie les observateurs.
     */
    public void update() {
        notifyObservers();
    }

    /**
     * Vérifie si cette entité entre en collision avec une autre entité.
     *
     * @param other l'autre entité à tester
     * @return {@code true} si les entités se chevauchent, {@code false} sinon
     * @throws IllegalArgumentException si l'entité donnée est nulle
     */
    public boolean overlaps(Entity other) {
        if (other == null) {
            throw new IllegalArgumentException("Other entity cannot be null.");
        }
        return hitbox.overlaps(other.hitbox);
    }

    /**
     * Modifie la position de l'entité avec un vecteur de vélocité.
     *
     * @param velocity la vélocité à ajouter à la position de l'entité
     */
    public void add(Vector2 velocity) {
        hitbox.add(velocity);
    }

    // Setters et Getters

    public void setTexture(Texture texture) {
        if (texture == null) {
            throw new IllegalArgumentException("Texture cannot be null.");
        }
        this.texture = texture;
    }

    public void setHealth(int health) {
        if (health <= 0) {
            throw new IllegalArgumentException("Health must be greater than zero.");
        }
        this.health = health;
    }

    public void setBatch(SpriteBatch batch) {
        if (batch == null) {
            throw new IllegalArgumentException("SpriteBatch cannot be null.");
        }
        this.batch = batch;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setFiringPoint(Vector2 firingPoint) {
        if (firingPoint == null) {
            throw new IllegalArgumentException("Firing point cannot be null.");
        }
        this.firingPoint = firingPoint;
    }

    public Vector2 getPosition() {
        return hitbox.getPosition();
    }

    public void setScore(int score) {}

    public void setVect(Vector2 vect) {}

    public void setX(float x) {
        hitbox.setX(x);
    }

    public void setY(float y) {
        hitbox.setY(y);
    }

    //Getters
    public float getX() {
        return hitbox.getX();
    }

    public float getY() {
        return hitbox.getY();
    }

    public float getWidth() {
        return size.x;
    }

    public float getHeight() {
        return size.y;
    }

    public int getHealth() {
        return health;
    }

    public Texture getTexture() {
        return texture;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Vector2 getFiringPoint() {
        return firingPoint;
    }

    public int getScore() {
        return 0;
    }

    public Vector2 getVect() {
        return null;
    }
}



