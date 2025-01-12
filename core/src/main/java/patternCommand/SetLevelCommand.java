package patternCommand;

import com.badlogic.gdx.Game;
import screens.ScreenType;

/**
 * Commande permettant de définir un niveau dans le jeu.
 * Cette commande est utilisée pour définir le niveau à jouer et
 * changer l'écran du jeu vers l'écran de jeu.
 */
public class SetLevelCommand implements Command {

    /** Le nom du niveau à définir. */
    private String level;

    /** L'écran actuel à partir duquel la commande est exécutée. */
    private ScreenType screen;

    /**
     * Constructeur de la commande pour définir le niveau.
     *
     * @param screen L'écran depuis lequel cette commande est appelée.
     * @param level Le nom du niveau à définir.
     */
    public SetLevelCommand(ScreenType screen, String level) {
        this.screen = screen;
        this.level = level;
    }

    /**
     * Exécute la commande de définition du niveau.
     * Cette méthode définit le niveau à jouer et change l'écran vers
     * l'écran de jeu pour démarrer la partie.
     *
     * @see ScreenType#getGame()
     * @see ScreenType#setNextScreen(String)
     */
    @Override
    public void execute() {
        screen.getGame().setLevel(level); // Définit le niveau à jouer
        screen.setNextScreen("GameScreen"); // Change l'écran vers l'écran de jeu
    }
}

