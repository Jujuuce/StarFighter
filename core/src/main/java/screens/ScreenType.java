package screens;

import com.badlogic.gdx.Screen;
import game.*;
import ui.ScoresManager;

import java.util.HashMap;

/**
 * La classe abstraite `ScreenType` implémente l'interface `Screen` et sert de base pour toutes les
 * écrans du jeu. Elle fournit des méthodes communes pour gérer les transitions entre les écrans,
 * la gestion du score, et certaines opérations de cycle de vie d'un écran.
 */
public abstract class ScreenType implements Screen {
    private String nextScreen = null; // Écran suivant à afficher
    private boolean firstShow; // Indique si c'est la première fois que l'écran est affiché
    private StarFighter game; // Référence à l'instance du jeu

    /**
     * Constructeur de la classe `ScreenType`.
     * Initialise l'écran avec une référence au jeu `StarFighter`.
     *
     * @param game L'instance du jeu associée à cet écran.
     */
    public ScreenType(StarFighter game) {
        this.game = game;
    }

    /**
     * Méthode abstraite à implémenter dans les sous-classes pour définir l'action à effectuer
     * lorsque cet écran est actif.
     *
     * @param game L'instance du jeu.
     * @return Le nom de l'écran suivant à afficher après l'action.
     */
    public abstract String doAction(StarFighter game);

    /**
     * Ajoute un score pour un niveau donné.
     *
     * @param level Le niveau pour lequel ajouter le score.
     * @param score Le score à ajouter.
     */
    public void addScore(String level, int score) {
        game.getScoresManager().addScore(level, score);
    }

    // Méthodes implémentées de l'interface Screen, mais non utilisées dans cette classe

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}

    @Override
    public void show() {}

    @Override
    public void render(float delta) {}

    @Override
    public void hide() {
        nextScreen = null;
    }

    // Setters

    /**
     * Définit si cet écran est le premier à être affiché.
     *
     * @param firstShow `true` si c'est le premier affichage, sinon `false`.
     */
    public void setFirstShow(boolean firstShow) {
        this.firstShow = firstShow;
    }

    /**
     * Définit l'écran suivant à afficher.
     *
     * @param nextScreen Le nom de l'écran suivant.
     */
    public void setNextScreen(String nextScreen) {
        this.nextScreen = nextScreen;
    }

    /**
     * Définit l'instance du jeu associée à cet écran.
     *
     * @param game L'instance du jeu.
     */
    public void setGame(StarFighter game) {
        this.game = game;
    }

    // Getters

    /**
     * Retourne le nom de l'écran suivant à afficher.
     *
     * @return Le nom de l'écran suivant.
     */
    public String getNextScreen() {
        return nextScreen;
    }

    /**
     * Retourne `true` si cet écran est le premier à être affiché, sinon `false`.
     *
     * @return `true` si c'est le premier affichage, sinon `false`.
     */
    public boolean isFirstShow() {
        return firstShow;
    }

    /**
     * Retourne l'instance du jeu associée à cet écran.
     *
     * @return L'instance du jeu.
     */
    public StarFighter getGame() {
        return game;
    }

    /**
     * Retourne les scores de tous les niveaux sous forme de `HashMap`.
     *
     * @return Une `HashMap` contenant les scores pour chaque niveau.
     */
    public HashMap<String, Integer> getScores() {
        return game.getScores();
    }

    /**
     * Retourne le score pour un niveau donné.
     *
     * @param level Le niveau pour lequel obtenir le score.
     * @return Le score du niveau, ou 0 si le niveau n'existe pas.
     */
    public int getScore(String level) {
        if (getScores().containsKey(level)) {
            return game.getScore(level);
        } else {
            return 0;
        }
    }
}

