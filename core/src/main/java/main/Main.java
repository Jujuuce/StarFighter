package main;

import com.badlogic.gdx.*;
import game.StarFighter;
import screens.*;


/**
 * La classe principale du jeu qui implémente `ApplicationListener` de libGDX.
 * Elle est responsable de la gestion des différents écrans du jeu et de leur transition.
 * Cette classe initie le jeu en enregistrant et en changeant les écrans en fonction des actions du joueur.
 */
public class Main implements ApplicationListener {
    private StarFighter game;

    /**
     * Méthode appelée lors de la création de l'application.
     * Elle initialise le jeu, enregistre les différents écrans et définit l'écran de départ.
     */
    @Override
    public void create() {
        game = new StarFighter();
        game.registerScreen("MainMenuScreen", new MainMenuScreen(game));
        game.registerScreen("GameScreen", new GameScreen(game));
        game.registerScreen("GameOverScreen", new GameOverScreen(game));
        game.registerScreen("PauseScreen", new PauseScreen(game));
        game.registerScreen("ClosingScreen", new ClosingScreen(game));
        game.registerScreen("StartScreen", new StartScreen(game));
        game.registerScreen("LevelSelectionScreen", new LevelSelectionScreen(game));
        game.registerScreen("OptionScreen", new OptionScreen(game));
        game.registerScreen("AchievementScreen", new AchievementScreen(game));

        game.changeScreen("StartScreen");
    }

    /**
     * Méthode appelée à chaque image pour rendre l'écran actuel et gérer les transitions.
     * Elle exécute la logique du jeu et change d'écran si nécessaire.
     */
    @Override
    public void render() {
        String nextScreen = game.execute();
        if (nextScreen != null) {
            game.changeScreen(nextScreen);
        }
        game.getCurrentScreen().render(Gdx.graphics.getDeltaTime());
    }

    /**
     * Méthode appelée lorsque la taille de la fenêtre change.
     * Elle ajuste la taille de l'écran actuel en fonction des nouvelles dimensions.
     *
     * @param width la nouvelle largeur de la fenêtre
     * @param height la nouvelle hauteur de la fenêtre
     */
    @Override
    public void resize(int width, int height) {
        game.getCurrentScreen().resize(width, height);
    }

    /**
     * Méthode appelée lorsque l'application est mise en pause.
     * Elle met en pause l'écran actuel.
     */
    @Override
    public void pause() {
        game.getCurrentScreen().pause();
    }

    /**
     * Méthode appelée lorsque l'application est reprise.
     * Elle reprend l'écran actuel.
     */
    @Override
    public void resume() {
        game.getCurrentScreen().resume();
    }

    /**
     * Méthode appelée lors de la fermeture de l'application.
     * Elle nettoie les ressources utilisées par l'écran actuel.
     */
    @Override
    public void dispose() {
        game.getCurrentScreen().dispose();
    }

    // Setter

    /**
     * Définit le jeu (StarFighter) à utiliser.
     *
     * @param game le jeu à définir
     */
    public void setGame(StarFighter game) {
        this.game = game;
    }

    // Getter

    /**
     * Récupère l'instance du jeu (StarFighter).
     *
     * @return l'instance du jeu
     */
    public StarFighter getGame() {
        return game;
    }
}
