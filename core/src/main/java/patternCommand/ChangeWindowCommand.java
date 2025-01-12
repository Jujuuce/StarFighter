package patternCommand;

import screens.ScreenType;

/**
 * Commande qui permet de changer le mode de fenêtre (plein écran / fenêtre) dans le jeu.
 * Cette classe implémente l'interface {@link Command} et encapsule l'action de basculer entre le mode fenêtre
 * et le mode plein écran lorsque la méthode {@link #execute()} est appelée.
 * <p>
 * Lors de l'exécution, cette commande vérifie l'état actuel de la fenêtre (fenêtre ou plein écran) et change
 * l'état en conséquence. Après avoir effectué ce changement, la commande redirige vers l'écran des options.
 * </p>
 */
public class ChangeWindowCommand implements Command {

    /** L'objet représentant l'écran actuel. */
    private ScreenType screen;

    /**
     * Constructeur qui crée une nouvelle commande pour changer le mode de fenêtre.
     *
     * @param screen L'écran actuel qui gère la logique du changement de fenêtre.
     */
    public ChangeWindowCommand(ScreenType screen) {
        this.screen = screen;
    }

    /**
     * Exécute la commande pour changer le mode de fenêtre.
     * Cette méthode bascule entre le mode fenêtre et le mode plein écran en fonction de l'état actuel.
     * Une fois le mode changé, elle redirige vers l'écran des options.
     */
    @Override
    public void execute() {
        // Vérifie si le jeu est en mode fenêtre ou plein écran
        if (screen.getGame().getVisuals().isWindowed()) {
            screen.getGame().getVisuals().setFullScreen(); // Passe en plein écran
        } else {
            screen.getGame().getVisuals().setWindowed(); // Passe en mode fenêtre
        }
        // Redirige vers l'écran des options après avoir changé le mode de fenêtre
        screen.setNextScreen("OptionScreen");
    }
}
