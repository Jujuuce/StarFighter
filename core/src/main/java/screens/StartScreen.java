package screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.StarFighter;
import patternCommand.ChangeScreenCommand;
import patternCommand.SetSaveCommand;
import ui.ScoresManager;

/**
 * `StartScreen` représente l'écran de démarrage du jeu, où l'utilisateur peut choisir une sauvegarde à charger
 * ou quitter le jeu. Cet écran propose des options pour charger des parties sauvegardées et quitter le jeu.
 */
public class StartScreen extends MenuScreens {

    /**
     * Constructeur de `StartScreen`.
     * Initialise l'écran avec l'instance du jeu.
     *
     * @param game L'instance du jeu associée à cet écran.
     */
    public StartScreen(StarFighter game) {
        super(game);
    }

    /**
     * Méthode appelée lorsque l'écran devient visible.
     * Elle configure les boutons pour charger une sauvegarde spécifique ou quitter le jeu.
     * Elle associe également les commandes pour chaque bouton.
     */
    @Override
    public void show() {
        super.show();  // Appelle la méthode show() de la classe parente pour initialiser le stage et le processeur d'entrées

        // Création des boutons pour charger les sauvegardes ou quitter le jeu
        createButton(100, 400, "Save 1", new SetSaveCommand(this, "save1.ser"));
        createButton(100, 300, "Save 2", new SetSaveCommand(this, "save2.ser"));
        createButton(100, 200, "Save 3", new SetSaveCommand(this, "save3.ser"));
        createButton(100, 100, "Quit", new ChangeScreenCommand(this, "ClosingScreen"));

        // Affichage du titre du jeu et du titre de l'écran de chargement de sauvegarde
        renderStarFighter();
        renderTitle("Load Save", 100, 500);

        // Lecture de la musique de fond du menu
        getGame().playMusic("menu");
    }

    /**
     * Méthode exécutée pour déterminer l'écran suivant lorsque l'action est effectuée.
     * Cette méthode est appelée lorsque l'utilisateur effectue une action de sélection d'une sauvegarde ou de quitter le jeu.
     *
     * @param game L'instance du jeu.
     * @return Le nom de l'écran suivant à afficher.
     */
    @Override
    public String doAction(StarFighter game) {
        return getNextScreen();  // Retourne l'écran suivant à afficher
    }
}

