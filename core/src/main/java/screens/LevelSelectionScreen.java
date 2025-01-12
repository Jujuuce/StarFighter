package screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.StarFighter;
import patternCommand.ChangeScreenCommand;
import patternCommand.SetLevelCommand;

/**
 * `LevelSelectionScreen` représente l'écran de sélection des niveaux dans le jeu. L'utilisateur peut choisir
 * entre plusieurs niveaux prédéfinis ou opter pour un mode de jeu sans fin. Cet écran offre des options pour
 * commencer un niveau ou revenir au menu principal.
 */
public class LevelSelectionScreen extends MenuScreens {

    /**
     * Constructeur de `LevelSelectionScreen`.
     * Initialise l'écran avec l'instance du jeu.
     *
     * @param game L'instance du jeu associée à cet écran.
     */
    public LevelSelectionScreen(StarFighter game) {
        super(game);
    }

    /**
     * Méthode appelée lorsque l'écran devient visible.
     * Elle configure les boutons pour choisir un niveau spécifique ou revenir au menu principal.
     * Elle associe également les commandes pour chaque bouton.
     */
    @Override
    public void show() {
        super.show();  // Appelle la méthode show() de la classe parente pour initialiser le stage et le processeur d'entrées

        // Création des boutons pour choisir un niveau ou revenir au menu principal
        createButton(100, 400, "Level 1", new SetLevelCommand(this, "Level1.tmx"));
        createButton(100, 300, "Level 2", new SetLevelCommand(this, "Level2.tmx"));
        createButton(100, 200, "Level 3", new SetLevelCommand(this, "Level3.tmx"));
        createButton(400, 400, "Endless", new SetLevelCommand(this, "Endless.tmx"));
        createButton(100, 100, "Back", new ChangeScreenCommand(this, "MainMenuScreen"));

        // Assurer que le jeu n'est pas en pause
        getGame().setPaused(false);

        // Affichage du titre de l'écran de sélection de niveau et du titre du jeu
        renderStarFighter();
        renderTitle("Choose A Level", 100, 500);
    }

    /**
     * Méthode exécutée pour déterminer l'écran suivant lorsque l'action est effectuée.
     * Cette méthode est appelée lorsque l'utilisateur sélectionne un niveau ou choisit de revenir au menu principal.
     *
     * @param game L'instance du jeu.
     * @return Le nom de l'écran suivant à afficher.
     */
    @Override
    public String doAction(StarFighter game) {
        return getNextScreen();  // Retourne l'écran suivant à afficher
    }
}


