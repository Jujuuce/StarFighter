package patternCommand;

import screens.GameScreen;
import screens.ScreenType;

/**
 * Commande qui permet de changer d'écran dans le jeu.
 * Cette classe implémente l'interface {@link Command} et encapsule l'action de changer l'écran actuel
 * vers un autre écran spécifié.
 * <p>
 * Cette commande est utilisée pour modifier l'état de l'écran dans un jeu ou une application, en
 * dirigeant l'exécution vers un écran particulier lorsque la méthode {@link #execute()} est appelée.
 * </p>
 */
public class ChangeScreenCommand implements Command {

    /** Nom de l'écran vers lequel l'application doit basculer. */
    private String screenName;

    /** L'objet représentant l'écran actuel. */
    private ScreenType screen;

    /**
     * Constructeur qui crée une nouvelle commande pour changer d'écran.
     *
     * @param screen L'écran actuel utilisé pour gérer le changement d'écran.
     * @param screenName Le nom de l'écran vers lequel changer.
     */
    public ChangeScreenCommand(ScreenType screen, String screenName) {
        this.screen = screen;
        this.screenName = screenName;
    }

    /**
     * Exécute la commande pour changer d'écran.
     * Cette méthode invoque {@link ScreenType#setNextScreen(String)} pour effectuer le changement
     * vers l'écran spécifié.
     */
    @Override
    public void execute() {
        screen.setNextScreen(screenName);
    }
}
