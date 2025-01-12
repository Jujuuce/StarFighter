package screens;

import game.*;
import patternCommand.ChangeScreenCommand;

/**
 * `MainMenuScreen` représente l'écran principal du menu du jeu. Cet écran permet à l'utilisateur de naviguer vers
 * d'autres écrans, tels que le menu de sélection de niveau, les réalisations, les options, et l'écran de démarrage.
 * Il gère également la lecture de la musique du menu et l'affichage des boutons interactifs.
 */
public class MainMenuScreen extends MenuScreens {

    /**
     * Constructeur de `MainMenuScreen`.
     * Initialise l'écran avec l'instance du jeu.
     *
     * @param game L'instance du jeu associée à cet écran.
     */
    public MainMenuScreen(StarFighter game) {
        super(game);
    }

    /**
     * Méthode appelée lorsque l'écran devient visible.
     * Elle configure les boutons du menu et lance la musique du menu.
     */
    @Override
    public void show() {
        super.show();  // Appelle la méthode show() de la classe parente pour initialiser le stage et le processeur d'entrées
        getGame().setPaused(false);  // Définir l'état du jeu comme non-paused
        getGame().stopMusic("game");  // Arrêter la musique du jeu
        getGame().playMusic("menu");  // Joue la musique du menu

        // Création des boutons de navigation dans le menu
        createButton(100, 400, "New Game", new ChangeScreenCommand(this, "LevelSelectionScreen"));
        createButton(100, 300, "Achievements", new ChangeScreenCommand(this, "AchievementScreen"));
        createButton(100, 200, "Options", new ChangeScreenCommand(this, "OptionScreen"));
        createButton(100, 100, "Back", new ChangeScreenCommand(this, "StartScreen"));

        // Affichage du titre du jeu et du titre du menu
        renderStarFighter();
        renderTitle("Main Menu", 100, 500);
    }

    /**
     * Méthode exécutée pour déterminer l'écran suivant lorsque l'action est effectuée.
     * Cette méthode est appelée lorsque l'utilisateur effectue une action de navigation.
     *
     * @param game L'instance du jeu.
     * @return Le nom de l'écran suivant à afficher.
     */
    @Override
    public String doAction(StarFighter game) {
        return getNextScreen();  // Retourne l'écran suivant à afficher
    }
}

