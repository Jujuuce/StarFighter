package patternCommand;

import com.badlogic.gdx.math.Vector2;
import mvc.PlayerController;

/**
 * Commande permettant de déplacer un joueur dans une direction donnée.
 * Cette commande est utilisée pour changer la position d'un joueur en fonction
 * de la direction spécifiée, par exemple pour des mouvements dans le jeu comme
 * avancer, reculer, ou se déplacer latéralement.
 */
public class MoveCommand implements Command {

    /** Le contrôleur du joueur qui est responsable du changement de position. */
    private PlayerController player;

    /** La direction dans laquelle le joueur doit se déplacer. */
    private Vector2 direction;

    /**
     * Constructeur de la commande de déplacement.
     *
     * @param player Le contrôleur du joueur à déplacer.
     * @param direction La direction du déplacement du joueur (vecteur).
     */
    public MoveCommand(PlayerController player, Vector2 direction) {
        this.player = player;
        this.direction = direction;
    }

    /**
     * Exécute la commande de déplacement en modifiant la position du joueur
     * selon la direction spécifiée.
     *
     * @see PlayerController#changePos(Vector2)
     */
    @Override
    public void execute() {
        player.changePos(direction);
    }
}
