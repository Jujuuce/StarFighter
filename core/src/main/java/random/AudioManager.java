package random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import java.util.HashMap;

/**
 * Gestionnaire audio pour le jeu, utilisant le pattern Singleton pour garantir une seule instance.
 * Cette classe permet de charger, jouer, mettre en pause, reprendre et arrêter les sons et musiques,
 * ainsi que de gérer le volume global du jeu.
 */
public class AudioManager {

    /** Instance unique du singleton */
    private static AudioManager instance;

    /** Volume global de l'audio (entre 0.0 et 1.0) */
    private float globalVolume;

    /** Map pour stocker les sons par leur clé */
    private final HashMap<String, Sound> sounds;

    /** Map pour stocker les musiques par leur clé */
    private final HashMap<String, Music> musics;

    /**
     * Constructeur privé pour initialiser le gestionnaire audio et charger les sons et musiques par défaut.
     *
     * @throws Exception si un fichier audio par défaut ne peut pas être chargé.
     */
    private AudioManager() {
        globalVolume = 1.0f; // Volume initial
        sounds = new HashMap<>();
        musics = new HashMap<>();

        // Charger les sons et musiques par défaut
        try {
            loadSound("hit", "Sounds/hit.ogg");
            loadSound("click", "Sounds/MenuClick.ogg");
            loadSound("fire", "Sounds/sfx_laser1.ogg");
            loadMusic("menu", "Sounds/menu.ogg");
            loadMusic("game", "Sounds/game.mp3");
        } catch (Exception e) {
            Gdx.app.error("AudioManager", "Error loading default audio files", e);
        }
    }

    /**
     * Retourne l'instance unique du gestionnaire audio.
     * Si l'instance n'existe pas, elle est créée.
     *
     * @return l'instance unique de {@link AudioManager}.
     */
    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    /**
     * Charge un fichier audio de type "son" dans le gestionnaire.
     *
     * @param key La clé sous laquelle le son sera stocké.
     * @param filePath Le chemin du fichier audio (relatif à la racine des ressources).
     * @throws IllegalArgumentException si le fichier audio n'existe pas.
     */
    public void loadSound(String key, String filePath) {
        if (!Gdx.files.internal(filePath).exists()) {
            throw new IllegalArgumentException("Sound file does not exist: " + filePath);
        }
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(filePath));
        sounds.put(key, sound);
    }

    /**
     * Joue un son donné par sa clé.
     *
     * @param key La clé du son à jouer.
     */
    public void playSound(String key) {
        Sound sound = sounds.get(key);
        if (sound != null) {
            sound.play(globalVolume);
        } else {
            Gdx.app.log("AudioManager", "Sound not found: " + key);
        }
    }

    /**
     * Charge un fichier audio de type "musique" dans le gestionnaire.
     *
     * @param key La clé sous laquelle la musique sera stockée.
     * @param filePath Le chemin du fichier audio (relatif à la racine des ressources).
     * @throws IllegalArgumentException si le fichier audio n'existe pas.
     */
    public void loadMusic(String key, String filePath) {
        if (!Gdx.files.internal(filePath).exists()) {
            throw new IllegalArgumentException("Music file does not exist: " + filePath);
        }
        Music music = Gdx.audio.newMusic(Gdx.files.internal(filePath));
        musics.put(key, music);
    }

    /**
     * Joue une musique donnée par sa clé, avec l'option de boucle.
     *
     * @param key La clé de la musique à jouer.
     * @param looping Indique si la musique doit être jouée en boucle.
     */
    public void playMusic(String key, boolean looping) {
        Music music = musics.get(key);
        if (music != null) {
            music.setLooping(looping);
            music.setVolume(globalVolume);
            music.play();
        } else {
            Gdx.app.log("AudioManager", "Music not found: " + key);
        }
    }

    /**
     * Met en pause une musique donnée par sa clé.
     *
     * @param key La clé de la musique à mettre en pause.
     */
    public void pauseMusic(String key) {
        Music music = musics.get(key);
        if (music != null) {
            music.pause();
        }
    }

    /**
     * Reprend la lecture d'une musique donnée par sa clé.
     *
     * @param key La clé de la musique à reprendre.
     */
    public void resumeMusic(String key) {
        Music music = musics.get(key);
        if (music != null) {
            music.play();
        }
    }

    /**
     * Arrête la lecture d'une musique donnée par sa clé.
     *
     * @param key La clé de la musique à arrêter.
     */
    public void stopMusic(String key) {
        Music music = musics.get(key);
        if (music != null) {
            music.stop();
        }
    }

    /**
     * Change le volume global de l'audio.
     *
     * @param volume La valeur du volume à définir (entre 0.0 et 1.0).
     */
    public void setGlobalVolume(float volume) {
        globalVolume = MathUtils.clamp(volume, 0f, 1f);

        // Appliquer le nouveau volume aux musiques en cours de lecture
        for (Music music : musics.values()) {
            if (music.isPlaying()) {
                music.setVolume(globalVolume);
            }
        }
    }

    /**
     * Retourne le volume global actuel.
     *
     * @return Le volume global.
     */
    public float getGlobalVolume() {
        return globalVolume;
    }

    /**
     * Libère toutes les ressources audio (sons et musiques) utilisées par le gestionnaire.
     * Cela permet de libérer la mémoire lorsque les ressources ne sont plus nécessaires.
     */
    public void disposeAll() {
        for (Sound sound : sounds.values()) {
            sound.dispose();
        }
        sounds.clear();

        for (Music music : musics.values()) {
            music.dispose();
        }
        musics.clear();
    }
}


