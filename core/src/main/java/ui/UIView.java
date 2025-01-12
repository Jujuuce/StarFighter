package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import observer.Observer;
import random.Visuals;

/**
 * La classe UIView représente la vue de l'interface utilisateur (UI) du jeu.
 * Elle est responsable de l'affichage des informations sur l'écran, telles que le score, la santé et le temps écoulé.
 * Elle observe l'objet UI et met à jour l'affichage lorsque l'UI change.
 */
public class UIView implements Observer {

    /**
     * L'instance de l'objet UI qui contient les données du joueur (score, santé, etc.).
     */
    private final UI ui;

    /**
     * L'instance de la classe Visuals qui gère le rendu graphique.
     */
    private Visuals visuals;

    /**
     * Constructeur de la classe UIView.
     * Enregistre cette vue comme observateur de l'UI, ce qui permettra à cette classe de se mettre à jour lorsque l'UI change.
     *
     * @param ui L'instance de l'objet UI qui contient les données du joueur.
     * @param visuals L'instance de la classe Visuals pour le rendu graphique.
     */
    public UIView(UI ui, Visuals visuals) {
        this.ui = ui;
        this.visuals = visuals;
        ui.add(this); // Enregistrement de l'observateur
    }

    /**
     * Méthode appelée pour mettre à jour l'affichage de l'interface utilisateur.
     * Cette méthode dessine une bande noire en haut de l'écran et affiche les informations telles que le score, la santé et le temps écoulé.
     * Elle est appelée chaque fois que l'UI notifie un changement.
     */
    @Override
    public void update() {
        // Obtenir les dimensions de l'écran
        float screenWidth = com.badlogic.gdx.Gdx.graphics.getWidth();
        float screenHeight = com.badlogic.gdx.Gdx.graphics.getHeight();
        float barHeight = screenHeight * 0.05f; // Hauteur de la barre en haut de l'écran

        // Dessiner la bande noire en haut de l'écran pour afficher les informations
        visuals.getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
        visuals.getShapeRenderer().setColor(Color.BLACK); // Couleur de la bande
        visuals.getShapeRenderer().rect(0, 0, screenWidth, barHeight); // Dessiner la barre
        visuals.getShapeRenderer().end();

        // Commencer le rendu du texte avec SpriteBatch
        visuals.getBatch().begin();

        // Afficher le score à 10% de la largeur de l'écran et à 80% de la hauteur de la barre
        visuals.renderText("Score: " + ui.getScore(), screenWidth * 0.1f, barHeight * 0.8f);

        // Afficher la santé à 80% de la largeur de l'écran
        visuals.renderText("Health: " + ui.getHealth(), screenWidth * 0.8f, barHeight * 0.8f);

        // Afficher le temps écoulé à 40% de la largeur de l'écran
        visuals.renderText(String.format("Time: %.1f s", ui.getElapsedTime()), screenWidth * 0.4f, barHeight * 0.8f);

        visuals.getBatch().end(); // Fin du rendu du texte
    }
}


