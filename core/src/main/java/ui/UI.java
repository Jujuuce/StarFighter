package ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entity.Player;
import observer.Observable;
import observer.Observer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe représentant l'interface utilisateur (UI) du jeu. Elle est responsable de l'affichage
 * des informations relatives au joueur (score, santé, etc.) et de la gestion des mises à jour
 * de l'affichage en fonction du temps écoulé et de l'état du joueur.
 * <p>
 * La classe hérite de {@link Observable}, ce qui lui permet de notifier ses observateurs chaque
 * fois qu'une mise à jour est effectuée.
 */
public class UI extends Observable {
    private Player player;           // Référence au joueur
    private SpriteBatch batch;       // SpriteBatch utilisé pour le rendu graphique
    private float elapsedTime;       // Temps écoulé depuis le début du jeu en secondes

    /**
     * Constructeur de la classe UI.
     *
     * @param player Le joueur dont les informations (score, santé) seront affichées.
     * @param batch Le SpriteBatch utilisé pour dessiner les éléments de l'UI.
     */
    public UI(Player player, SpriteBatch batch) {
        this.player = player;
        this.batch = batch;
        this.elapsedTime = 0;  // Initialisation du temps écoulé à 0
    }

    /**
     * Méthode de mise à jour de l'UI. Elle calcule le temps écoulé et notifie les observateurs.
     */
    public void update() {
        float deltaTime = com.badlogic.gdx.Gdx.graphics.getDeltaTime();  // Temps écoulé depuis la dernière frame
        elapsedTime += deltaTime;  // Ajoute le temps écoulé au total
        notifyObservers();         // Notifie les observateurs que l'UI a été mise à jour
    }

    // Setters

    /**
     * Définit le joueur dont les informations seront affichées dans l'UI.
     *
     * @param player Le joueur.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Définit le SpriteBatch utilisé pour dessiner les éléments de l'UI.
     *
     * @param batch Le SpriteBatch.
     */
    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    /**
     * Définit le temps écoulé depuis le début du jeu.
     *
     * @param elapsedTime Le temps écoulé en secondes.
     */
    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    // Getters

    /**
     * Obtient le joueur dont les informations sont affichées dans l'UI.
     *
     * @return Le joueur.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Obtient le SpriteBatch utilisé pour dessiner les éléments de l'UI.
     *
     * @return Le SpriteBatch.
     */
    public SpriteBatch getBatch() {
        return batch;
    }

    /**
     * Obtient le temps écoulé depuis le début du jeu.
     *
     * @return Le temps écoulé en secondes.
     */
    public float getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Obtient le score actuel du joueur.
     *
     * @return Le score du joueur.
     */
    public int getScore() {
        return player.getScore();
    }

    /**
     * Obtient la santé actuelle du joueur.
     *
     * @return La santé du joueur.
     */
    public int getHealth() {
        return player.getHealth();
    }
}
