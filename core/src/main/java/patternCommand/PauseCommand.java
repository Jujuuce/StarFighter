package patternCommand;

import screens.GameScreen;

/**
 * Commande permettant de mettre en pause le jeu.
 * Cette commande est utilisée pour basculer l'état du jeu entre une session
 * en pause et une reprise du jeu, par exemple lors de l'appui sur une touche de pause.
 */
public class PauseCommand implements Command {

    /** L'écran de jeu associé à cette commande, permettant de contrôler l'état du jeu. */
    private GameScreen gameScreen;

    /**
     * Constructeur de la commande de pause.
     *
     * @param gameScreen L'écran de jeu à mettre en pause ou reprendre.
     */
    public PauseCommand(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    /**
     * Exécute la commande de pause en appelant la méthode de l'écran de jeu pour
     * basculer entre les états "pause" et "reprendre".
     *
     * @see GameScreen#pause()
     */
    @Override
    public void execute() {
        gameScreen.pause();  // Bascule entre pause et reprise du jeu
    }
}
