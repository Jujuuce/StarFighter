package screens;

import game.*;
import patternCommand.ChangeScreenCommand;


/**
 * `GameOverScreen` représente l'écran de fin de jeu qui s'affiche lorsque le joueur perd. Il permet au joueur
 * de voir son score actuel par rapport au meilleur score et propose de revenir au menu principal.
 */
public class GameOverScreen extends MenuScreens {
    private int score;
    private int previousScore;

    /**
     * Constructeur de `GameOverScreen`.
     * Initialise l'écran avec l'instance du jeu.
     *
     * @param game L'instance du jeu associée à cet écran.
     */
    public GameOverScreen(StarFighter game) {
        super(game);
    }

    /**
     * Méthode appelée lorsque l'écran devient visible.
     * Elle affiche les informations du score et propose de revenir au menu principal.
     */
    @Override
    public void show() {
        super.show();  // Appelle la méthode show() de la classe parente pour initialiser le stage et le processeur d'entrées

        // Arrêter la musique de jeu et jouer la musique du menu
        getGame().stopMusic("game");
        getGame().playMusic("menu");

        // Création du bouton pour revenir au menu principal
        createButton(100, 100, "Main menu", new ChangeScreenCommand(this, "MainMenuScreen"));

        // Affichage du titre du jeu et du message de fin
        renderStarFighter();
        renderTitle("Game Over", 100, 500);

        // Récupérer le score du joueur et comparer avec le meilleur score
        previousScore = getScore(getGame().getLevel());
        checkScores();
    }

    /**
     * Méthode pour vérifier si le joueur a battu son meilleur score.
     * Si oui, le score est mis à jour et un message est affiché.
     * Si non, les scores sont affichés.
     */
    public void checkScores() {
        if (score > previousScore) {
            // Nouveau meilleur score
            renderText("New Best Score : " + score, 400, 400);
            addScore(getGame().getLevel(), score);
        } else {
            // Score actuel par rapport au meilleur score
            renderText("Your score : " + score, 400, 400);
            renderText("Best score : " + previousScore, 400, 300);
        }
    }

    /**
     * Méthode exécutée pour déterminer l'écran suivant lorsque l'action est effectuée.
     * Cette méthode est appelée lorsque l'utilisateur choisit de revenir au menu principal.
     *
     * @param game L'instance du jeu.
     * @return Le nom de l'écran suivant à afficher.
     */
    @Override
    public String doAction(StarFighter game) {
        return getNextScreen();  // Retourne l'écran suivant à afficher
    }

    /**
     * Définit le score actuel.
     *
     * @param score Le score à définir.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Définit le précédent score du joueur.
     *
     * @param previousScore Le précédent score à définir.
     */
    public void setPreviousScore(int previousScore) {
        this.previousScore = previousScore;
    }

    /**
     * Définit à la fois le score actuel et le précédent score du joueur.
     *
     * @param score Le score actuel.
     * @param previousScore Le précédent score.
     */
    public void setScores(int score, int previousScore) {
        setScore(score);
        setPreviousScore(previousScore);
    }

    /**
     * Récupère le précédent score du joueur.
     *
     * @return Le précédent score.
     */
    public int getPreviousScore() {
        return previousScore;
    }
}

