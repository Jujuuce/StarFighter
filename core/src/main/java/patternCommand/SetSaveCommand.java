package patternCommand;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import game.StarFighter;
import screens.ScreenType;

/**
 * Commande permettant de définir l'emplacement du fichier de sauvegarde.
 * Cette commande configure le chemin de sauvegarde en fonction du répertoire de travail actuel
 * et définit le fichier de sauvegarde pour le jeu.
 */
public class SetSaveCommand implements Command {

    /** Le nom du fichier de sauvegarde à définir. */
    private String save;

    /** L'écran actuel à partir duquel la commande est exécutée. */
    private ScreenType screen;

    /**
     * Constructeur de la commande pour définir la sauvegarde.
     *
     * @param screen L'écran depuis lequel cette commande est appelée.
     * @param save Le nom du fichier de sauvegarde à définir.
     */
    public SetSaveCommand(ScreenType screen, String save) {
        this.screen = screen;
        this.save = save;
    }

    /**
     * Exécute la commande pour définir l'emplacement du fichier de sauvegarde.
     * Cette méthode détermine le répertoire de travail actuel et choisit le chemin approprié
     * pour le fichier de sauvegarde, puis définit ce fichier dans le jeu.
     *
     * @see System#getProperty(String)
     * @see ScreenType#setNextScreen(String)
     */
    @Override
    public void execute() {
        String savePath;

        // Obtenir le répertoire de travail actuel
        String currentDir = System.getProperty("user.dir");

        // Déterminer le chemin de sauvegarde en fonction du répertoire actuel
        if (currentDir.endsWith("assets")) {
            // Si le répertoire courant est 'assets' (terminal)
            savePath = "Saves/" + save;
        } else {
            // Sinon, on suppose que nous sommes dans le répertoire racine (IntelliJ)
            savePath = "assets/Saves/" + save;
        }
        System.out.println("Répertoire de travail actuel : " + System.getProperty("user.dir"));

        // Définir le chemin de sauvegarde pour le jeu
        screen.getGame().setSave(savePath);
        // Changer d'écran vers le menu principal
        screen.setNextScreen("MainMenuScreen");
    }
}

