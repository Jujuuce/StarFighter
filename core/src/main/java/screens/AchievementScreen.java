package screens;

import game.StarFighter;
import patternCommand.ChangeScreenCommand;

import java.util.Map;

/**
 * `AchievementScreen` représente l'écran des réalisations du jeu. Il affiche les scores des différents niveaux joués,
 * permettant au joueur de voir ses meilleures performances pour chaque niveau.
 */
public class AchievementScreen extends MenuScreens {

    /**
     * Constructeur de `AchievementScreen`.
     * Initialise l'écran des réalisations avec l'instance du jeu.
     *
     * @param game L'instance du jeu associée à cet écran.
     */
    public AchievementScreen(StarFighter game) {
        super(game);
    }

    /**
     * Méthode qui configure et affiche l'écran des réalisations.
     * Elle affiche les scores des différents niveaux du jeu, ainsi qu'un bouton pour revenir au menu principal.
     */
    @Override
    public void show() {
        // Obtient les dimensions de l'écran
        float screenWidth = com.badlogic.gdx.Gdx.graphics.getWidth();
        float screenHeight = com.badlogic.gdx.Gdx.graphics.getHeight();

        super.show(); // Appel de la méthode super pour configurer le stage et le processeur d'entrée

        // Création d'un bouton "Back" pour revenir au menu principal
        createButton(100, 100, "Back", new ChangeScreenCommand(this, "MainMenuScreen"));

        // Affichage de l'arrière-plan et du titre
        renderStarFighter();
        renderTitle("Achievements", 100, 500);

        // Commence à dessiner les textes
        getGame().getVisuals().getBatch().begin();

        // Affichage des scores pour chaque niveau
        renderText("Level 1 : " + getScores().get("Level1.tmx").toString(), screenWidth * 0.4f, screenHeight * 0.6f);
        renderText("Level 2 : " + getScores().get("Level2.tmx").toString(), screenWidth * 0.4f, screenHeight * 0.5f);
        renderText("Level 3 : " + getScores().get("Level3.tmx").toString(), screenWidth * 0.4f, screenHeight * 0.4f);
        renderText("Endless : " + getScores().get("Endless.tmx").toString(), screenWidth * 0.4f, screenHeight * 0.3f);

        // Fin du dessin des textes
        getGame().getVisuals().getBatch().end();
    }

    /**
     * Méthode exécutée pour effectuer l'action de l'écran des réalisations.
     * Elle retourne le prochain écran à afficher, en l'occurrence celui défini dans la méthode `getNextScreen`.
     *
     * @param game L'instance du jeu.
     * @return Le prochain écran à afficher.
     */
    @Override
    public String doAction(StarFighter game) {
        return getNextScreen();
    }
}


