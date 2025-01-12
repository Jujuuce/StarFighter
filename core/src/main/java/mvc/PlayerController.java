package mvc;

import com.badlogic.gdx.math.Vector2;
import entity.*;

import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;
import patternCommand.*;
import projectileFactory.*;
import projectileStrategy.Projectile1Strategy;
import projectileStrategy.ProjectileStrategyPlayer1;
import random.EntityManager;
import screens.GameScreen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import java.util.Map;

/**
 * Contrôleur pour gérer les actions du joueur dans le jeu. Ce contrôleur gère les entrées utilisateur, comme les déplacements et les tirs.
 * Il est responsable de l'interprétation des commandes et de l'exécution des actions associées, telles que le déplacement et le tir.
 */
public class PlayerController extends Controller {
    private InputHandler inputHandler;
    private GameScreen gameScreen;

    /**
     * Constructeur de la classe `PlayerController`.
     * Initialise le contrôleur pour le joueur, configure les touches et lie les commandes associées.
     *
     * @param entity L'entité associée au contrôleur (le joueur).
     * @param entityManager Le gestionnaire des entités.
     * @param gameScreen L'écran du jeu.
     */
    public PlayerController(Entity entity, EntityManager entityManager, GameScreen gameScreen) {
        super(entity, entityManager);
        this.gameScreen = gameScreen;
        inputHandler = new InputHandler();
        setProjectileStrategy(new ProjectileStrategyPlayer1());
        setInputs();
    }

    /**
     * Gère les entrées utilisateur et met à jour les commandes du joueur.
     * Cette méthode est appelée régulièrement pour traiter les actions du joueur.
     */
    public void handleInput() {
        increaseDelay();
        inputHandler.handleInput();
    }

    /**
     * Définit les touches d'entrée associées aux actions du joueur en récupérant les mappages de touches dynamiques.
     */
    public void setInputs() {
        Map<String, String> inputs = gameScreen.getGame().getInputs();
        setUP(inputs.get("up"));
        setDOWN(inputs.get("down"));
        setLEFT(inputs.get("left"));
        setRIGHT(inputs.get("right"));
        setFIRE(inputs.get("fire"));
        setPAUSE(inputs.get("pause"));
    }

    /**
     * Lie une touche pressée à une commande.
     *
     * @param key La touche à lier.
     * @param command La commande à exécuter lorsque la touche est pressée.
     */
    private void bindKeyPressed(String key, Command command) {
        int keyCode = getKeyCode(key);
        if (keyCode == Input.Keys.UNKNOWN) {
            throw new IllegalArgumentException("Touche inconnue: " + key);
        }
        inputHandler.bindKeyPressed(keyCode, command);
    }

    /**
     * Lie une touche juste pressée à une commande.
     *
     * @param key La touche à lier.
     * @param command La commande à exécuter lorsque la touche est juste pressée.
     */
    private void bindKeyJustPressed(String key, Command command) {
        int keyCode = getKeyCode(key);
        if (keyCode == Input.Keys.UNKNOWN) {
            throw new IllegalArgumentException("Touche inconnue: " + key);
        }
        inputHandler.bindKeyJustPressed(keyCode, command);
    }

    /**
     * Définit la touche pour le mouvement vers le haut.
     *
     * @param key La touche associée au mouvement vers le haut.
     */
    public void setUP(String key) {
        bindKeyPressed(key, new MoveCommand(this, new Vector2(0, 10)));
    }

    /**
     * Définit la touche pour le mouvement vers le bas.
     *
     * @param key La touche associée au mouvement vers le bas.
     */
    public void setDOWN(String key) {
        bindKeyPressed(key, new MoveCommand(this, new Vector2(0, -10)));
    }

    /**
     * Définit la touche pour le mouvement vers la gauche.
     *
     * @param key La touche associée au mouvement vers la gauche.
     */
    public void setLEFT(String key) {
        bindKeyPressed(key, new MoveCommand(this, new Vector2(-10, 0)));
    }

    /**
     * Définit la touche pour le mouvement vers la droite.
     *
     * @param key La touche associée au mouvement vers la droite.
     */
    public void setRIGHT(String key) {
        bindKeyPressed(key, new MoveCommand(this, new Vector2(10, 0)));
    }

    /**
     * Définit la touche pour tirer.
     *
     * @param key La touche associée à l'action de tir.
     */
    public void setFIRE(String key) {
        bindKeyPressed(key, new FireCommand(this));
    }

    /**
     * Définit la touche pour mettre le jeu en pause.
     *
     * @param key La touche associée à l'action de pause.
     */
    public void setPAUSE(String key) {
        bindKeyJustPressed(key, new PauseCommand(gameScreen));
    }

    /**
     * Récupère le code de la touche en fonction de son nom.
     *
     * @param keyName Le nom de la touche.
     * @return Le code de la touche.
     */
    public static int getKeyCode(String keyName) {
        try {
            // Récupère le champ correspondant dans Input.Keys
            return Input.Keys.class.getField(keyName.toUpperCase()).getInt(null);
        } catch (Exception e) {
            // Retourne une valeur par défaut (ex: Input.Keys.UNKNOWN) si la touche est inconnue
            return Input.Keys.UNKNOWN;
        }
    }

    // Getter

    /**
     * Retourne l'écran du jeu associé au contrôleur.
     *
     * @return L'écran du jeu.
     */
    public GameScreen getGameScreen() {
        return gameScreen;
    }
}

