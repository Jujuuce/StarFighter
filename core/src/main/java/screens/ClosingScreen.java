package screens;

import com.badlogic.gdx.Gdx;
import game.*;

/**
 * `ClosingScreen` représente l'écran de fermeture du jeu. Il est utilisé pour quitter le jeu lorsque l'utilisateur
 * choisit de fermer l'application.
 */
public class ClosingScreen extends ScreenType {

    /**
     * Constructeur de `ClosingScreen`.
     * Initialise l'écran avec l'instance du jeu.
     *
     * @param game L'instance du jeu associée à cet écran.
     */
    public ClosingScreen(StarFighter game) {
        super(game);
    }

    /**
     * Méthode exécutée lorsque l'action de fermer le jeu est effectuée.
     * Cette méthode est appelée pour quitter le jeu en fermant l'application.
     *
     * @param game L'instance du jeu.
     * @return Toujours `null` car il n'y a pas de prochain écran à afficher.
     */
    @Override
    public String doAction(StarFighter game) {
        Gdx.app.exit();  // Quitte l'application
        return null;      // Pas d'écran suivant
    }
}
