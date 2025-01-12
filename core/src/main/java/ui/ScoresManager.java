package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;

import java.io.*;
import java.util.HashMap;

import java.io.*;
import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Classe responsable de la gestion des scores du jeu. Elle permet de charger,
 * sauvegarder et manipuler les scores des différents niveaux.
 *
 * Le gestionnaire de scores lit et écrit les scores dans un fichier, et permet
 * d'ajouter de nouveaux scores en fonction des performances des joueurs.
 */
public class ScoresManager {
    private HashMap<String, Integer> scores;
    private String fileName;

    /**
     * Constructeur qui charge les scores depuis un fichier ou crée un fichier par défaut si nécessaire.
     *
     * @param fileName Le nom du fichier de sauvegarde des scores.
     */
    public ScoresManager(String fileName) {
        this.fileName = fileName;

        // Tenter de désérialiser le fichier en utilisant le chemin du fichier approprié
        try {
            FileHandle fileHandle = Gdx.files.internal(fileName);
            File file = new File(fileHandle.path());

            if (file.exists()) {
                // Si le fichier existe, le lire
                try (FileInputStream fileIn = new FileInputStream(file);
                     ObjectInputStream in = new ObjectInputStream(fileIn)) {
                    scores = (HashMap<String, Integer>) in.readObject();
                    System.out.println("Scores chargés depuis le fichier : " + scores);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                // Si le fichier n'existe pas, créer un nouveau fichier avec une HashMap vide
                System.out.println("Fichier non trouvé. Création d'un fichier par défaut.");
                scores = new HashMap<>();
                scores.put("Level1.tmx", 0);
                scores.put("Level2.tmx", 0);
                scores.put("Level3.tmx", 0);
                scores.put("Endless.tmx", 0);
                saveScores(); // Créer le fichier immédiatement
            }

        } catch (Exception e) {
            e.printStackTrace();
            scores = new HashMap<>(); // Initialiser à une HashMap vide en cas d'autres erreurs
        }
    }

    /**
     * Sauvegarde les scores actuels dans le fichier.
     */
    public void saveScores() {
        try {
            FileHandle fileHandle = Gdx.files.local(fileName);
            File file = new File(fileHandle.path());

            if (!file.exists()) {
                file.createNewFile();  // Créer le fichier si nécessaire
            }

            try (FileOutputStream fileOut = new FileOutputStream(file);
                 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(scores);
                System.out.println("Scores sauvegardés dans le fichier : " + scores);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajoute un score pour un niveau donné si le nouveau score est plus élevé que le score actuel.
     *
     * @param level Le nom du niveau.
     * @param score Le score à ajouter.
     */
    public void addScore(String level, int score) {
        if (scores.get(level) < score) {
            scores.put(level, score);
            saveScores();
        }
    }

    // Setters

    /**
     * Définit les scores à une nouvelle HashMap de scores.
     *
     * @param scores La nouvelle HashMap contenant les scores.
     */
    public void setScores(HashMap<String, Integer> scores) {
        this.scores = scores;
    }

    /**
     * Définit le nom du fichier de sauvegarde des scores.
     *
     * @param fileName Le nouveau nom du fichier.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    // Getters

    /**
     * Obtient le nom du fichier de sauvegarde des scores.
     *
     * @return Le nom du fichier de sauvegarde des scores.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Obtient la HashMap contenant tous les scores des niveaux.
     *
     * @return La HashMap contenant les scores.
     */
    public HashMap<String, Integer> getScores() {
        return scores;
    }

    /**
     * Obtient le score pour un niveau donné.
     *
     * @param level Le nom du niveau.
     * @return Le score du niveau, ou 0 si le score n'est pas trouvé.
     */
    public int getScore(String level) {
        if (!scores.containsKey(level)) {
            System.out.println("Score inconnu pour le niveau " + level);
            return 0;
        }
        return scores.get(level);
    }
}

