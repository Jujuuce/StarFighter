package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import game.*;
import patternCommand.ChangeScreenCommand;
import patternCommand.InputHandler;


/**
 * `PauseScreen` représente l'écran de pause du jeu. Cet écran permet à l'utilisateur de naviguer
 * entre différentes options pendant une pause, telles que reprendre le jeu, accéder aux options, ou revenir au menu principal.
 */
public class PauseScreen extends MenuScreens {
    private InputHandler inputHandler;

    /**
     * Constructeur de `PauseScreen`.
     * Initialise l'écran avec l'instance du jeu.
     *
     * @param game L'instance du jeu associée à cet écran.
     */
    public PauseScreen(StarFighter game) {
        super(game);
    }

    /**
     * Méthode appelée lorsque l'écran devient visible.
     * Elle configure les boutons du menu de pause et l'état du jeu en pause.
     * Elle associe également les commandes pour naviguer dans le menu de pause.
     */
    @Override
    public void show() {
        super.show();  // Appelle la méthode show() de la classe parente pour initialiser le stage et le processeur d'entrées
        createButton(100, 400, "Back To Game", new ChangeScreenCommand(this, "GameScreen"));
        createButton(100, 300, "Options", new ChangeScreenCommand(this, "OptionScreen"));
        createButton(100, 200, "Main menu", new ChangeScreenCommand(this, "MainMenuScreen"));

        // Affichage du titre du jeu et du titre de l'écran de pause
        renderStarFighter();
        renderTitle("Pause", 100, 500);

        // Mise en pause du jeu
        getGame().setPaused(true);

        // Initialisation de l'inputHandler pour gérer les entrées
        inputHandler = new InputHandler();
        inputHandler.bindKeyJustPressed(Input.Keys.ESCAPE, new ChangeScreenCommand(this, "GameScreen"));
    }

    /**
     * Méthode exécutée pour déterminer l'écran suivant lorsque l'action est effectuée.
     * Cette méthode est appelée lorsque l'utilisateur effectue une action de navigation dans le menu de pause.
     *
     * @param game L'instance du jeu.
     * @return Le nom de l'écran suivant à afficher.
     */
    @Override
    public String doAction(StarFighter game) {
        return getNextScreen();  // Retourne l'écran suivant à afficher
    }

    /**
     * Méthode exécutée pour rendre l'écran et gérer les entrées utilisateur.
     * Elle appelle `handleInput()` de `inputHandler` pour vérifier si l'utilisateur appuie sur une touche.
     *
     * @param delta Le temps écoulé entre les images, utilisé pour l'animation.
     */
    @Override
    public void render(float delta) {
        super.render(delta);

        // Gère les entrées utilisateur
        if (inputHandler != null) {
            inputHandler.handleInput();
        }
    }
}

