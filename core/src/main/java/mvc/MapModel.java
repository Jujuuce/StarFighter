package mvc;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import observer.*;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.MapObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.MapObjects;


/**
 * Modèle représentant une carte dans le jeu. Il charge la carte depuis un fichier et notifie ses observateurs
 * lorsqu'il y a des changements.
 */
public class MapModel extends Observable {
    private TiledMap map;

    /**
     * Constructeur de la classe `MapModel`.
     * Ce constructeur charge une carte à partir d'un fichier spécifié par son chemin.
     *
     * @param path Le chemin relatif du fichier de la carte à charger.
     */
    public MapModel(String path) {
        try {
            // Tente de charger la carte et capture les erreurs potentielles
            map = new TmxMapLoader().load("Levels/" + path);
            if (map == null) {
                throw new IllegalArgumentException("La carte n'a pas pu être chargée.");
            }
        } catch (Exception e) {
            // Loggez l'erreur de chargement pour déboguer
            System.err.println("Erreur lors du chargement de la carte: " + e.getMessage());
        }
    }

    /**
     * Met à jour le modèle et notifie les observateurs que des changements ont eu lieu.
     */
    public void update() {
        // Notifie les observateurs que le modèle a changé
        notifyObservers();
    }

    // Getter

    /**
     * Retourne la carte chargée.
     *
     * @return La carte (`TiledMap`) chargée.
     */
    public TiledMap getMap() {
        return map;
    }

    /**
     * Retourne les objets présents dans la couche "Objects" de la carte.
     * Si cette couche n'existe pas, une liste vide d'objets est retournée.
     *
     * @return Les objets dans la couche "Objects".
     */
    public MapObjects getObjects() {
        if (map != null && map.getLayers().get("Objects") != null) {
            return map.getLayers().get("Objects").getObjects();
        }
        return new MapObjects();  // Retourner un objet vide si aucune couche "Objects" n'est trouvée
    }
}
