package mvc;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import entity.*;
import exceptions.ResourceUnavailableException;
import observer.*;
import hitbox.*;


/**
 * La classe `View` est un observateur qui gère l'affichage graphique d'une entité dans le jeu.
 * Elle se charge de rendre l'entité à l'écran en utilisant un renderer de forme et une batch pour le rendu des textures.
 */
public class View implements Observer {
    private Entity entity;
    private ShapeRenderer shapeRenderer;

    /**
     * Constructeur de la classe `View`.
     * Initialise la vue avec l'entité spécifiée et s'abonne aux notifications de l'entité.
     *
     * @param entity L'entité associée à cette vue. Ne peut pas être null.
     * @throws IllegalArgumentException Si l'entité est null.
     */
    public View(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        this.entity = entity;
        shapeRenderer = new ShapeRenderer();
        entity.add(this);  // S'abonne aux notifications de l'entité.
    }

    /**
     * Méthode appelée lorsque l'entité est mise à jour.
     * Appelle la méthode de dessin et gère le rendu de l'entité dans le batch.
     */
    @Override
    public void update() {
        if (entity == null) {
            throw new IllegalStateException("Entity is not set correctly");
        }
        entity.beginBatch();

        drawBatch();  // Dessine l'entité sur le batch.

        entity.endBatch();
    }

    /**
     * Dessine l'entité sur le batch, en utilisant sa texture et ses coordonnées.
     * Cette méthode vérifie que la texture et le batch sont disponibles avant de dessiner.
     *
     * @throws ResourceUnavailableException Si le batch ou la texture de l'entité n'est pas disponible.
     */
    public void drawBatch() {
        if (entity.getBatch() == null || entity.getTexture() == null) {
            throw new ResourceUnavailableException("Batch or Texture is not available");
        }
        entity.getBatch().draw(entity.getTexture(), entity.getX(), entity.getY());
    }

    // Setters

    /**
     * Définit l'entité associée à cette vue.
     *
     * @param entity L'entité à associer à cette vue. Ne peut pas être null.
     * @throws IllegalArgumentException Si l'entité est null.
     */
    public void setEntity(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        this.entity = entity;
    }

    /**
     * Définit le `ShapeRenderer` utilisé pour dessiner les formes.
     *
     * @param shapeRenderer Le `ShapeRenderer` à utiliser. Ne peut pas être null.
     * @throws IllegalArgumentException Si le `ShapeRenderer` est null.
     */
    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        if (shapeRenderer == null) {
            throw new IllegalArgumentException("ShapeRenderer cannot be null");
        }
        this.shapeRenderer = shapeRenderer;
    }

    // Getters

    /**
     * Retourne le `ShapeRenderer` utilisé pour dessiner les formes.
     *
     * @return Le `ShapeRenderer` utilisé pour dessiner les formes.
     */
    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    /**
     * Retourne l'entité associée à cette vue.
     *
     * @return L'entité associée à cette vue.
     */
    public Entity getEntity() {
        return entity;
    }
}


