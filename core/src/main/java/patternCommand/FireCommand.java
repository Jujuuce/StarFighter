package patternCommand;

import com.badlogic.gdx.math.Vector2;
import mvc.PlayerController;

/**
 * Commande qui permet de tirer un projectile dans le jeu.
 * Cette classe implémente l'interface {@link Command} et encapsule l'action de tirer un projectile lorsque la méthode
 * {@link #execute()} est appelée.
 * <p>
 * La commande vérifie d'abord si le délai entre les tirs est suffisant (défini par la logique du jeu) avant de permettre
 * un tir. Si le délai est respecté, elle crée un projectile et l'ajoute à la liste des contrôleurs, puis réinitialise
 * le délai pour permettre un nouveau tir après un certain temps.
 * </p>
 */
public class FireCommand implements Command {

    /** Le contrôleur du joueur qui effectue l'action de tirer. */
    private PlayerController controller;

    /**
     * Constructeur qui crée une nouvelle commande de tir pour un joueur.
     *
     * @param controller Le contrôleur du joueur qui gère les actions de tir.
     */
    public FireCommand(PlayerController controller) {
        this.controller = controller;
    }

    /**
     * Exécute la commande de tir.
     * Cette méthode vérifie si le délai entre les tirs est suffisant. Si oui, elle crée un projectile
     * et l'ajoute à la liste des contrôleurs. Ensuite, elle réinitialise le délai pour permettre un nouveau tir.
     *
     * <p>Le projectile est créé avec une direction définie par un vecteur (dans cet exemple, 50 pixels devant le joueur).</p>
     */
    @Override
    public void execute() {
        // Vérifie si le délai entre les tirs est suffisant
        if (controller.getDelay() > 10) {
            // Définir un vecteur de direction de tir, ici vers la droite ou selon la direction de l'entité
            Vector2 targetPoint = new Vector2(controller.getEntity().getX(), controller.getEntity().getY() + 50); // Exemple: tirer 50 pixels devant le joueur

            // Créer un projectile et l'ajouter à la liste des contrôleurs
            controller.getGameScreen().getLists().addController(controller.fireProjectile(targetPoint));

            // Réinitialiser le délai après le tir
            controller.resetDelay();
        }
    }
}
