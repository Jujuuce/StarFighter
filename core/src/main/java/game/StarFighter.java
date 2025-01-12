package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import patternCommand.InputHandler;
import random.AudioManager;
import random.Visuals;
import screens.*;
import ui.ScoresManager;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Main class for the StarFighter game.
 *
 * <p>This class extends {@link com.badlogic.gdx.Game} and serves as the central point for managing
 * the game's screens, assets, inputs, and audio. It also handles the game state, such as pause and level management.</p>
 */
public class StarFighter extends Game {

    private ScreenType currScreen;
    private final HashMap<String, ScreenType> screens = new HashMap<>();
    private ScoresManager scoresManager;
    private String save;
    private String level;
    private boolean isPaused = false;
    private Visuals visuals;
    private HashMap<String, String> inputs;
    private AudioManager audioManager;

    /**
     * Constructs a new {@code StarFighter} game instance.
     * <p>
     * Initializes default input mappings and audio manager.
     * </p>
     */
    public StarFighter() {
        this.visuals = new Visuals();
        this.inputs = new HashMap<>();
        inputs.put("up", "W");
        inputs.put("down", "S");
        inputs.put("left", "A");
        inputs.put("right", "D");
        inputs.put("fire", "Space");
        inputs.put("pause", "Escape");
        audioManager = AudioManager.getInstance();
    }

    /**
     * Registers a screen in the game.
     *
     * @param key    the identifier for the screen
     * @param screen the {@link ScreenType} to register
     */
    public void registerScreen(String key, ScreenType screen) {
        screens.put(key, screen);
    }

    /**
     * Changes the currently active screen.
     *
     * @param screenDesc the key of the screen to switch to
     * @throws IllegalArgumentException if the screen key does not exist
     */
    public void changeScreen(String screenDesc) {
        if (!screens.containsKey(screenDesc)) {
            throw new IllegalArgumentException("Screen not found: " + screenDesc);
        }
        if (this.currScreen != null) this.currScreen.hide();
        setCurrScreen(this.screens.get(screenDesc));
        this.currScreen.setFirstShow(true);
        if (currScreen != null) {
            setScreen(currScreen);
        }
    }

    /**
     * Executes the current screen's action and returns the result.
     *
     * @return the result of the screen's action
     */
    public String execute() {
        return currScreen.doAction(this);
    }

    /**
     * Sets the score for the Game Over screen.
     *
     * @param score the score to display on the Game Over screen
     */
    public void setGameOverScore(int score) {
        ((GameOverScreen) screens.get("GameOverScreen")).setScore(score);
    }

    @Override
    public void create() {}

    /**
     * Plays the specified background music.
     *
     * @param music the name of the music file
     */
    public void playMusic(String music) {
        audioManager.playMusic(music, true);
    }

    /**
     * Stops the specified background music.
     *
     * @param music the name of the music file
     */
    public void stopMusic(String music) {
        audioManager.stopMusic(music);
    }

    /**
     * Plays the specified sound effect.
     *
     * @param sound the name of the sound file
     */
    public void playSound(String sound) {
        audioManager.playSound(sound);
    }

    // Setters and Getters

    /**
     * Sets the {@link ScoresManager} instance.
     *
     * @param scoresManager the {@code ScoresManager} to set
     * @throws IllegalArgumentException if the provided {@code scoresManager} is null
     */
    public void setScoresManager(ScoresManager scoresManager) {
        if (scoresManager == null) {
            throw new IllegalArgumentException("ScoreManager can't be null");
        }
        this.scoresManager = scoresManager;
    }

    /**
     * Sets the current active screen.
     *
     * @param currScreen the screen to set as current
     * @throws IllegalStateException if the provided screen is null
     */
    public void setCurrScreen(ScreenType currScreen) {
        if (currScreen == null) {
            throw new IllegalStateException("Current screen is not set");
        }
        this.currScreen = currScreen;
    }

    /**
     * Sets the current level.
     *
     * @param level the name of the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Sets the current save file and initializes a new {@link ScoresManager}.
     *
     * @param save the save file path
     */
    public void setSave(String save) {
        this.save = save;
        setScoresManager(new ScoresManager(save));
    }

    /**
     * Toggles the pause state of the game.
     *
     * @param paused {@code true} to pause the game; {@code false} to unpause
     */
    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    /**
     * Sets the {@link Visuals} configuration.
     *
     * @param visuals the visuals configuration to set
     */
    public void setVisuals(Visuals visuals) {
        this.visuals = visuals;
    }

    /**
     * Sets the input mappings.
     *
     * @param inputs a {@link HashMap} containing input mappings
     */
    public void setInputs(HashMap<String, String> inputs) {
        this.inputs = inputs;
    }

    // Getters with detailed documentation

    /**
     * Returns the currently active screen.
     *
     * @return the current {@link ScreenType}
     * @throws IllegalStateException if the current screen is null
     */
    public ScreenType getCurrentScreen() {
        if (currScreen == null) {
            throw new IllegalStateException("Current screen is null. Please check the screen registration.");
        }
        return currScreen;
    }

    /**
     * Retrieves the current {@link ScoresManager}.
     *
     * @return the scores manager
     */
    public ScoresManager getScoresManager() {
        return scoresManager;
    }

    /**
     * Returns all registered screens.
     *
     * @return a {@link HashMap} of screen keys and their associated {@link ScreenType}s
     */
    public HashMap<String, ScreenType> getScreens() {
        return screens;
    }

    /**
     * Retrieves the current level name.
     *
     * @return the name of the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * Retrieves the save file path.
     *
     * @return the save file path
     */
    public String getSave() {
        return save;
    }

    /**
     * Retrieves the saved scores for all levels.
     *
     * @return a {@link HashMap} of level names and their scores
     */
    public HashMap<String, Integer> getScores() {
        return scoresManager.getScores();
    }

    /**
     * Retrieves the score for a specific level.
     *
     * @param level the level name
     * @return the score for the level
     */
    public int getScore(String level) {
        return scoresManager.getScore(level);
    }

    /**
     * Checks if the game is currently paused.
     *
     * @return {@code true} if the game is paused; {@code false} otherwise
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Retrieves the visuals configuration.
     *
     * @return the {@link Visuals} instance
     */
    public Visuals getVisuals() {
        return visuals;
    }

    /**
     * Retrieves the input mappings.
     *
     * @return a {@link HashMap} of actions and their corresponding keys
     */
    public HashMap<String, String> getInputs() {
        return inputs;
    }
}
