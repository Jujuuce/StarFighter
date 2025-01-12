package patternCommand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe qui gère l'entrée des touches et l'exécution des commandes associées.
 * Elle permet d'associer des commandes spécifiques à des touches pour gérer les interactions de l'utilisateur
 * dans le jeu, que ce soit pour des touches maintenues ou juste pressées.
 * <p>
 * Elle sépare les commandes selon le type d'interaction : celles activées par des touches maintenues
 * et celles activées par des touches juste pressées.
 * </p>
 */
public class InputHandler {

    /** Map pour les commandes associées aux touches maintenues. */
    private Map<Integer, Command> keyPressedCommands;

    /** Map pour les commandes associées aux touches juste pressées. */
    private Map<Integer, Command> keyJustPressedCommands;

    /**
     * Constructeur qui initialise les cartes de commandes.
     */
    public InputHandler() {
        keyPressedCommands = new HashMap<>();
        keyJustPressedCommands = new HashMap<>();
    }

    /**
     * Associe une commande à une touche pour être exécutée lorsqu'une touche est maintenue enfoncée.
     *
     * @param key La touche à associer à la commande.
     * @param command La commande à exécuter lorsque la touche est maintenue enfoncée.
     */
    public void bindKeyPressed(int key, Command command) {
        keyPressedCommands.put(key, command);
    }

    /**
     * Associe une commande à une touche pour être exécutée lorsqu'une touche est juste pressée.
     *
     * @param key La touche à associer à la commande.
     * @param command La commande à exécuter lorsque la touche est juste pressée.
     */
    public void bindKeyJustPressed(int key, Command command) {
        keyJustPressedCommands.put(key, command);
    }

    /**
     * Vérifie l'état des touches et exécute les commandes associées.
     * Cette méthode est appelée à chaque itération du jeu pour détecter et exécuter les actions des utilisateurs.
     */
    public void handleInput() {
        // Vérifie les commandes pour les touches maintenues
        for (Map.Entry<Integer, Command> entry : keyPressedCommands.entrySet()) {
            int key = entry.getKey();
            Command command = entry.getValue();

            if (Gdx.input.isKeyPressed(key)) {
                command.execute();
            }
        }

        // Vérifie les commandes pour les touches juste pressées
        for (Map.Entry<Integer, Command> entry : keyJustPressedCommands.entrySet()) {
            int key = entry.getKey();
            Command command = entry.getValue();

            if (Gdx.input.isKeyJustPressed(key)) {
                command.execute();
            }
        }
    }
}

